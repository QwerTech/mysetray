package ru.muzetray.zsearch.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Collections;

import static java.util.Arrays.asList;

@Configuration
@ComponentScan("ru.muzetray.zsearch.services")
@Slf4j
public class Config {
    @Bean
    @Scope(scopeName = "prototype")
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.setInterceptors(Collections.singletonList(
                (request, body, execution) -> {
                    log.debug("Requesting {}:{}",request.getMethod(),request.getURI());
                    return execution.execute(request, body);}));
        return restTemplate;
    }
}
