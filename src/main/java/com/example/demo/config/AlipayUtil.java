package com.example.demo.config;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;

/**
 * @Author: Wu Chenxuan
 * @Description: 支付宝的参数
 * @Date: 2019/10/30
 **/
public class AlipayUtil {

    //支付宝移动端appid
    public static final String ALIPAY_APP_APPID = "2016101700707603";

    //支付宝商户私钥
    public static final String ALIPAY_PRIVATE_KEY =
            "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCMPgVZxah9Xtgdv/jFsaJVFyyvThBy" +
                    "/Zw7mDfoYETefYt1amcKHITQ9mOBrHZNsA3jsMBo9fXH14w/+3VxV/wgczbiCVyXgGXSCwcATt" +
                    "/PhQq8CT4CHAYNhhfysgbEWCsf+piAFfYMRwNus/xFb+N2wt1PRhAvRCSqvgFPoJmgR5Iyn1sYnWNj8F0+90mlv4E" +
                    "+DslF104+0ysfjOzy2r0uhKsLxXpr2EZt5H2xH7rfMvgO0EjFulvlwi6BAcc7uJGLeNeA17QYkx6tDqrtZ2oqw0v6UTulDlnFRUS6eQd2rKYQtT/ThL7BoYmCmS1YMuQ7Qwy7JQNXi5+7CSO4mj9DAgMBAAECggEAKbGWraXR/dPgtJeKQlwZQurs4gF3anhzplQO6wsT7oGsoG/NynfIFMfrtXWshlQ9DOBTk+Dr/Unb7Hm55MSftJYMTw8xc+k8XEHjBvhsGaoYEuc8hCwIsKSZIhJDaMQ9rfg2tn3Q9L14C8V+zrYBDiHI4fSWgzbjH8/1W9MHWVr7V84SXLyzeGi3JLYlHRKpewANUm4wicK4CYTVg/qxFP/WWrURCQnMAO/HU026QPOF9mbN9zrk2sKHG6MDHoNtpTmDbWzz9jWua3iCeuW/yj/lBjx42/EVu3W0C5XBFr6+arUbWvr5WZm7Ck+qJ9+tLbpp1k8hnWSo/C+6w4C8wQKBgQDjQuA6s83rJ5iNDdNzfxvokJYYTe+n9mPq79jAEhNAu7nx/WtZ6UVyWbOwFqVyMvW2bLOr8UbdFGIvVh0hTRSgEWGqlUK1dUSz3ru1mYSTVrCoLDkW81P/BEn7Lkszoy1DjGxB19gtsMc/oMNk7FxoGq0l0gSnDGTHeav9wkXl6wKBgQCd+hTejo1aAF/OwRLjuHwX5Mrv5dUBd9mMO06mYG9V+0SA5JPjrNgH6mlZkmw7IXfe2vebwIVqdP1gk+y0oLgR8qo36xqgT52cWd/IWPsjI8BzsDkOJG6Zcie0HTWztTpCLP73XCLdod3anuVVVG0mIJdGL2GH6HlucvefvpL+CQKBgGFxFzuURMKDI8G3H+bxpUG+XNzm7c8SshzV4hXYqjCjTUMY6aKPhptVItppm16vwvMJALDEryGzz7WEMtFIf4NycHH3tbagJWkKI2nl/RY/oVc6PvA8PNOnPyFX5B6r5IiF+RTyO1q75XK+t4jaX3Szm3IP7rbBdaSh4sDXP6kpAoGAEZs1iDhK+KTiCKDsAgUlj2QoWeQ7gRZiYIablV9aCTMfx12yPcWdrgQqCZmQBmcMfQHaZrlmJR80vfa+gsMGwLlSzziclxFId4xsrF9+XcQ2Rq51LWZSDTfzxNccjRK9k3KAWAEkwZ6woxTiakgmggFP2rqdHBCEuBKIFn2e1EECgYAyyMF3G7zZCGXuY0u90csefCkA4unUYbya+ncKNg0x03EYK3chI+5JlvwLnMTi39YRbS99UL+pKqphlILBnczYxaLczb7Lr45vdCN1YwPTH+/t7bDfWblRER+DDp78wIWFf+/OGuB4MOjBhbM/0m91ZpriR+wZHWIP/BoXwq5Mrw==";

    //支付宝公钥
    public static final String ALIPAY_PUBLIC_KEY =
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjD4FWcWofV7YHb" +
                    "/4xbGiVRcsr04Qcv2cO5g36GBE3n2LdWpnChyE0PZjgax2TbAN47DAaPX1x9eMP" +
                    "/t1cVf8IHM24glcl4Bl0gsHAE7fz4UKvAk+AhwGDYYX8rIGxFgrH/qYgBX2DEcDbrP8RW" +
                    "/jdsLdT0YQL0Qkqr4BT6CZoEeSMp9bGJ1jY/BdPvdJpb+BPg7JRddOPtMrH4zs8tq9LoSrC8V6a9hGbeR9sR" +
                    "+63zL4DtBIxbpb5cIugQHHO7iRi3jXgNe0GJMerQ6q7WdqKsNL+lE7pQ5ZxUVEunkHdqymELU/04S" +
                    "+waGJgpktWDLkO0MMuyUDV4ufuwkjuJo/QwIDAQAB";

    //支付宝网关
    public static final String ALIPAY_GATEWAY_URL = "https://openapi.alipaydev.com/gateway.do";

    // 验证方式
    public static final String SIGN_TYPE = "RSA2";

    // 返回路径
    public static final String ALIPAY_NOTIFYURL = "https://www.alipy.com";

    // 传输方式
    public static final String FORMAT = "json";

    // 字符串格式
    public static final String CHARSET = "UTF-8";

    private static AlipayClient alipayClient = null;

    public static AlipayClient getAlipayClient() throws AlipayApiException {
        //实例化客户端
        if (alipayClient == null) {
            synchronized (AlipayUtil.class) {
                if (null == alipayClient) {
                    alipayClient = new DefaultAlipayClient(ALIPAY_GATEWAY_URL, ALIPAY_APP_APPID, ALIPAY_PRIVATE_KEY,
                            FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);
                }
            }
        }
        return alipayClient;
    }
}
