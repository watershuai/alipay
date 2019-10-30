package com.example.demo.controller;

import com.alipay.api.AlipayApiException;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradeRefundModel;
import com.alipay.api.internal.util.AlipaySignature;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeRefundRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeRefundResponse;
import com.example.demo.bean.AlipayDTO;
import com.example.demo.bean.ReFundDTO;
import com.example.demo.config.AlipayUtil;
import com.google.common.collect.Maps;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URLDecoder;
import java.util.Iterator;
import java.util.Map;

/**
 * @Author: Wu Chenxuan
 * @Description: 支付宝的app支付
 * @Date: 2019/10/30
 **/
@RestController
@RequestMapping("/aliPay")
public class AlipayController {

    private static final Logger log = LoggerFactory.getLogger(AlipayController.class);

    /**
     * 功能描述  下单支付
     *
     * @param alipayDTO
     * @return Map<String, Object>
     */
    @RequestMapping(value = "/aliPay", method = RequestMethod.POST)
    public Map<String, Object> aliPayOrder(@RequestBody AlipayDTO alipayDTO) {
        Map map = Maps.newHashMap();
        AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
        AlipayTradeAppPayModel model = new AlipayTradeAppPayModel();
        //(否)对一笔交易的具体描述信息。如果是多种商品，请将商品描述字符串累加传给body。
        model.setBody(alipayDTO.getBody());
        //(是)商品的标题/交易标题/订单标题/订单关键字等
        model.setSubject(alipayDTO.getSubject());
        //(是)商户网站唯一订单号
        model.setOutTradeNo(alipayDTO.getOutTradeNo());
        //(是)订单总金额，单位为元，精确到小数点后两位
        model.setTotalAmount(alipayDTO.getTotalAmount());
        //(否)设置未付款支付宝交易的超时时间，一旦超时，该笔交易就会自动被关闭。
        model.setTimeoutExpress("30m");
        //(是)销售产品码，商家和支付宝签约的产品码，为固定值QUICK_MSECURITY_PAY
        model.setProductCode("QUICK_MSECURITY_PAY");
        request.setBizModel(model);
        request.setNotifyUrl(AlipayUtil.ALIPAY_NOTIFYURL);
        try {
            //这里和普通的接口调用不同，使用的是sdkExecute
            AlipayTradeAppPayResponse responseStr = AlipayUtil.getAlipayClient().sdkExecute(request);
            //可以直接给客户端请求，无需再做处理。
            map.put("msg", responseStr.getBody());
        } catch (AlipayApiException e) {
            log.info("AliPayOrder exception={}", e);
        }
        return map;

    }

    /**
     * 功能描述 支付回调
     *
     * @param request
     * @return java.lang.String
     */
    @RequestMapping(value = "/aliPayNotify", method = RequestMethod.POST)
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
            boolean flag = AlipaySignature.rsaCheckV1(params, AlipayUtil.ALIPAY_PUBLIC_KEY, AlipayUtil.CHARSET,
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


    /**
     * 功能描述  退款申请
     *
     * @param reFundDTO
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/aliPayRefund", method = RequestMethod.POST)
    public Map<String, Object> aliPayRefund(@Valid @RequestBody ReFundDTO reFundDTO) {
        Map map = Maps.newHashMap();
        AlipayTradeRefundRequest request = new AlipayTradeRefundRequest();
        AlipayTradeRefundModel model = new AlipayTradeRefundModel();
        // 退款金额
        model.setRefundAmount(String.valueOf(reFundDTO.getRefundAmount()));
        // 支付宝交易号
        model.setTradeNo(reFundDTO.getTradeNo());
        // 订单支付时传入的商户订单号
        model.setOutTradeNo(reFundDTO.getOutTrandeNo());
        if (StringUtils.isNotBlank(reFundDTO.getRefundReason())) {
            // 退款原因
            model.setRefundReason(reFundDTO.getRefundReason());
        }
        request.setBizModel(model);
        try {
            AlipayTradeRefundResponse response = AlipayUtil.getAlipayClient().execute(request);
            map.put("response", URLDecoder.decode(response.getBody()));
            return map;
        } catch (AlipayApiException e) {
            log.info("aliPayRefund exception={}", e);
            e.printStackTrace();
        }
        return map;
    }


}
