package com.example.demo.controller;

import com.example.demo.bean.H5PayDTO;
import com.example.demo.util.H5PayUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: 吴宸煊
 * Date: 2020/2/24 21:38
 * Description: H5请求支付宝支付
 */
@RequestMapping("/pay")
@Controller
@Slf4j
public class H5PayController {

    @Autowired
    private H5PayUtil h5PayUtil;

    @PostMapping("/H5Pay")
    public String pay(@RequestBody H5PayDTO h5PayDto) {
        String result = h5PayUtil.aliPayH5(h5PayDto).getBody();
        log.info("支付宝返回的信息是:" + result);
        return result;
    }

    @RequestMapping("/H5PayNotice")
    public String orderNotify(HttpServletRequest request) {
        String alipayNotice = h5PayUtil.aliPayNotify(request);
        return alipayNotice;
    }
}
