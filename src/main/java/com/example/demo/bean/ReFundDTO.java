package com.example.demo.bean;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author: Wu Chenxuan
 * @Description: 退款参数
 * @Date: 2019/10/30
 **/
@Data
public class ReFundDTO implements Serializable {

    @NotNull(message = "缺少请求参数")
    public BigDecimal refundAmount;

    @NotNull(message = "缺少请求参数")
    public String outTrandeNo;

    @NotNull(message = "缺少请求参数")
    public String tradeNo;

    private String refundReason;

    public BigDecimal getRefundAmount() {
        return refundAmount;
    }

    public void setRefundAmount(BigDecimal refundAmount) {
        this.refundAmount = refundAmount;
    }

    public String getOutTrandeNo() {
        return outTrandeNo;
    }

    public void setOutTrandeNo(String outTrandeNo) {
        this.outTrandeNo = outTrandeNo;
    }

    public String getTradeNo() {
        return tradeNo;
    }

    public void setTradeNo(String tradeNo) {
        this.tradeNo = tradeNo;
    }

    public String getRefundReason() {
        return refundReason;
    }

    public void setRefundReason(String refundReason) {
        this.refundReason = refundReason;
    }
}
