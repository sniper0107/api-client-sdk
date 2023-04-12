package com.api.client.apiclientsdk.client;

import cn.hutool.core.util.RandomUtil;
import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson2.JSONObject;
import com.api.client.apiclientsdk.model.ResponseData;
import com.api.client.apiclientsdk.utils.SignUtil;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sniper
 * @date 2023-03-20 22:24
 * @description 调用第三方接口客户端
 */
public class ApiClient {

    private final String accessKey;
    private final String secretKey;

    public ApiClient(String accessKey, String secretKey) {
        this.accessKey = accessKey;
        this.secretKey = secretKey;
    }

    /**
     * get请求
     * @author sniper
     * @date 2023-03-29 17:06
     * @param url 请求地址
	 * @param paramMap 请求参数
     * @return 响应结果
     */
    public ResponseData get(String url, Map<String, Object> paramMap) {

        String paramJson = JSONObject.toJSONString(paramMap);
        String body = HttpRequest.get(url)
                .body(paramJson)
                .addHeaders(createHeaders(paramJson))
                .execute()
                .body();

        return JSONObject.parseObject(body, ResponseData.class);
    }

    /**
     * post请求
     * @author sniper
     * @date 2023-03-29 17:24
     * @param url 请求地址
     * @param paramMap 请求参数
     * @return 响应结果
     */
    public ResponseData post(String url, Map<String, Object> paramMap) {

        String paramJson = JSONObject.toJSONString(paramMap);
        String body = HttpRequest.post(url)
                .body(paramJson)
                .addHeaders(createHeaders(paramJson))
                .execute()
                .body();

        return JSONObject.parseObject(body, ResponseData.class);
    }

    /**
     * 生成请求头
     * @author sniper
     * @date 2023-03-29 17:20
     * @param body 请求参数
     * @return 请求头
     */
    private Map<String, String> createHeaders(String body) {

        Map<String, String> headers = new HashMap<>(4);
        headers.put("accessKey", accessKey);
        headers.put("nonce", RandomUtil.randomNumbers(10));
        headers.put("timestamp", String.valueOf(System.currentTimeMillis() / 1000));
        String json = JSONObject.toJSONString(headers);
        headers.put("sign", SignUtil.sign(body + json, secretKey));

        return headers;
    }
}
