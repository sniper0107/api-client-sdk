package com.api.client.apiclientsdk.utils;

import cn.hutool.crypto.digest.DigestAlgorithm;
import cn.hutool.crypto.digest.Digester;

/**
 * @author sniper
 * @date 2023-03-29 17:00
 * @description 签名工具类
 */
public class SignUtil {

    /**
     * 生成签名
     * @author sniper
     * @date 2023-03-29 17:02
     * @param body 请求体
	 * @param secretKey 密钥
     * @return 签名
     */
    public static String sign(String body, String secretKey) {
        Digester digester = new Digester(DigestAlgorithm.SHA256);
        return digester.digestHex(body + "." + secretKey);
    }
}
