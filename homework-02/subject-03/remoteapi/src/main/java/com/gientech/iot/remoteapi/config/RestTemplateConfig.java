package com.gientech.iot.remoteapi.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.client.ResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

/**
 * @description: RestTemplate配置
 * @author: 王强
 * @dateTime: 2022-12-13 21:52:49
 */
@Configuration
public class RestTemplateConfig {
    /**
     * http请求默认连接超时时间
     */
    final int DEFAULT_CONNECT_TIMEOUT = 3000;

    /**
     * http请求读数据超时时间
     */
    final int DEFAULT_READ_TIMEOUT = 3000;

    @Primary
    @Bean("restTemplate")
    public RestTemplate restTemplate() {
        RestTemplateBuilder builder = new RestTemplateBuilder();
        ResponseErrorHandler responseErrorHandler = new ResponseErrorHandler() {
            @Override
            public boolean hasError(ClientHttpResponse clientHttpResponse) {
                return true;
            }
            @Override
            public void handleError(ClientHttpResponse clientHttpResponse) {
            }
        };
        ClientHttpRequestInterceptor requestInterceptor = (httpRequest, bytes, clientHttpRequestExecution) -> {
            ClientHttpResponse execute;
            try {
                execute = clientHttpRequestExecution.execute(httpRequest, bytes);
            } catch (IOException e) {
                // 特殊处理超时异常
                if (e instanceof SocketTimeoutException) {
                    // 返回自定义异常
//                     throw new RuntimeException("请求超时");
                }
                throw e;
            }
            return execute;
        };
        return builder
                // 连接超时
                .setConnectTimeout(Duration.ofMillis(DEFAULT_CONNECT_TIMEOUT))
                // 读取超时
                .setReadTimeout(Duration.ofMillis(DEFAULT_READ_TIMEOUT))
                // 响应结果字符集
                .messageConverters(new StringHttpMessageConverter(StandardCharsets.UTF_8))
                // 自定义拦截器
                .interceptors(requestInterceptor)
                // 自定义异常处理
                .errorHandler(responseErrorHandler)
                .build();
    }

}
