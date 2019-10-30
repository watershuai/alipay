package com.example.demo.bean;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author: Wu Chenxuan
 * @Description: 下单参数
 * @Date: 2019/10/30
 **/
@Data
public class AlipayDTO implements Serializable {

    @NotNull(message = "缺少请求参数")
    private String subject;

    @NotNull(message = "缺少请求参数")
    private String outTradeNo;

    @NotNull(message = "缺少请求参数")
    private String totalAmount;

    private String body;

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getOutTradeNo() {
        return outTradeNo;
    }

    public void setOutTradeNo(String outTradeNo) {
        this.outTradeNo = outTradeNo;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
