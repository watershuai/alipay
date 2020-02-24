package com.example.demo.util;

import com.alipay.api.AlipayClient;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import com.example.demo.bean.H5PayDTO;
import com.example.demo.config.AlipayUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: 吴宸煊
 * Date: 2020/2/24 21:39
 * Description:
 */
@Slf4j
@Component
public class H5PayUtil {


    private static ObjectMapper mapper = new ObjectMapper();

    /**
     * H5请求支付宝支付
     * @param h5PayDto
     * @return
     */
    public AlipayTradeWapPayResponse aliPayH5(H5PayDTO h5PayDto) {
        try {
            AlipayClient alipayClient = AlipayUtil.getAlipayClient();
            AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
            request.setNotifyUrl("");
            request.setBizContent(getAliPayRequest(h5PayDto).toString());
            request.setReturnUrl(h5PayDto.getReturnUrl());
            AlipayTradeWapPayResponse responseStr = alipayClient.pageExecute(request);
            log.info(responseStr.getBody());
            return responseStr;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return  null;
    }


    private ObjectNode getAliPayRequest(H5PayDTO  h5PayDto) throws UnsupportedEncodingException {
        ObjectNode obj = mapper.createObjectNode();
        //obj.put("subject", URLEncoder.encode(payRequest.getBody(), "GBK"));
        obj.put("subject", h5PayDto.getSubject());
        obj.put("out_trade_no", h5PayDto.getOutTradeNo());
        obj.put("timeout_express", 30d);
        //obj.put("time_expire", DateTimeUtils.getYmdhmFormatDate(System.currentTimeMillis() + DateUtils.DAY_IN_MILLS));
        obj.put("total_amount", h5PayDto.getTotalAmount());
        obj.put("quit_url", h5PayDto.getReturnUrl());
        obj.put("product_code", "QUICK_WAP_PAY");
        return obj;
    }


    /**
     * 功能描述 支付回调
     *
     * @param request
     * @return java.lang.String
     */
    public String aliPayNotify(HttpServletRequest request) {
        Map<String, String> params = Maps.newHashMap();
        try {
            //获取支付宝POST过来反馈信息
            Map requestParams = request.getParameterMap();
            for (Iterator iter = requestParams.keySet().iterator(); iter.hasNext(); ) {
                String name = (String) iter.next();
                String[] values = (String[]) requestParams.get(name);
                String valueStr = "";
                for (int i = 0; i < values.length; i++) {
                    valueStr = (i == values.length - 1) ? valueStr + values[i]
                            : valueStr + values[i] + ",";
                }
                //乱码解决，这段代码在出现乱码时使用。
                valueStr = new String(valueStr.getBytes("ISO-8859-1"), "utf-8");
                params.put(name, valueStr);
            }
            //调用SDK验证签名
            boolean flag = AlipaySignature.rsaCheckV1(params, AlipayUtil.ALIPAY_PUBLIC_KEY,  AlipayUtil.CHARSET,
                    AlipayUtil.SIGN_TYPE);
            if (flag) { // 验证成功
                return "success";
            }

        } catch (Exception e) {
            log.info("aliPayNotify exception={}", e);
            return "fail";
        }
        return "fail";
    }

}
