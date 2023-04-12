package com.api.client.apiclientsdk.model;

import lombok.Data;

/**
 * @author sniper
 * @date 2023-03-29 17:04
 * @description 响应数据
 */
@Data
public class ResponseData {

    private Integer code;
    private Boolean success;
    private String msg;
    private Object data;
}
