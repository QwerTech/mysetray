package ru.muzetray.zsearch.zapi;

import feign.RequestInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;

public class FeignZapiConfiguration {

    @Bean
    public RequestInterceptor accessTokenAuthRequestInterceptor(@Lazy TokenStore store) {
        String debug_token = "WVh18sEasrVgy7CkM8ryE0ljqgXb7EiF2dhoYdzIJnad3vzuqMe8SsBVEWu-kc3R8gHNuUbwhRYpdKcDUNrzMCaKh8mHqrGXP8EFW94kTn0JzmHNSbcfBsQ2IvUu5nAfPYaEW1nggoNSo8spQSwQuh9-oH2-al5C7sQWi2B90NJGXjpTC4ygj-KODAlLsOPQ";
        return template -> {
            template.query("access_token", store.getToken());
        };
    }
}