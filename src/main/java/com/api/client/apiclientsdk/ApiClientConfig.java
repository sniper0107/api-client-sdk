package com.api.client.apiclientsdk;

import com.api.client.apiclientsdk.client.ApiClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author sniper
 * @date 2023-03-22 16:46
 * @description api客户端配置类
 */
@Data
@Configuration
@ConfigurationProperties("api.client")
@ComponentScan
public class ApiClientConfig {

    private String accessKey;

    private String secretKey;

    @Bean
    public ApiClient apiClient() {
        return new ApiClient(accessKey, secretKey);
    }
}
