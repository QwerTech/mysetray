package ru.muzetray.zsearch.zapi;

import feign.Logger;
import feign.RequestInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

@Slf4j
public class FeignZapiConfiguration {

    @Bean
    public RequestInterceptor accessTokenAuthRequestInterceptor(@Lazy TokenStore store) {
//        String debug_token = "WVh18sEasrVgy7CkM8ryE0ljqgXb7EiF2dhoYdzIJnad3vzuqMe8SsBVEWu-kc3R8gHNuUbwhRYpdKcDUNrzMCaKh8mHqrGXP8EFW94kTn0JzmHNSbcfBsQ2IvUu5nAfPYaEW1nggoNSo8spQSwQuh9-oH2-al5C7sQWi2B90NJGXjpTC4ygj-KODAlLsOPQ";
        return template -> template.query("access_token", store.getToken());
    }

    @Bean
    public RequestInterceptor requestLogInterceptor() {
        return template -> log.info("FeignZapi Request: {}", template);
    }
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}