package com.project.restbrewery.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import reactor.netty.http.client.HttpClient;

@Configuration
public class WebClient {
    @Bean
    public WebClient webClient() {
        assert WebClient.builder() != null;
        return (WebClient) WebClient.builder()
                .baseUrl(WebClientProperties.BASE_URL)
                .clientConnector(new ReactorClientHttpConnector(HttpClient.create()
                        .wiretap(true)))
                .build();
    }

    private static org.springframework.web.reactive.function.client.WebClient.Builder builder() {
        return null;
    }

    public org.springframework.web.reactive.function.client.WebClient.UriSpec<org.springframework.web.reactive.function.client.WebClient.RequestHeadersSpec<?>> get() {
        return null;
    }
}
