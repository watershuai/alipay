package com.example.demo.bean;

import lombok.Data;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Author: 吴宸煊
 * Date: 2020/2/24 21:36
 * Description: H5请求支付宝支付
 */
@Data
public class H5PayDTO  implements Serializable {
    @NotNull(message = "缺少请求参数")
    private String subject;

    @NotNull(message = "缺少请求参数")
    private String outTradeNo;

    @NotNull(message = "缺少请求参数")
    private String totalAmount;

    private String  returnUrl;
}
