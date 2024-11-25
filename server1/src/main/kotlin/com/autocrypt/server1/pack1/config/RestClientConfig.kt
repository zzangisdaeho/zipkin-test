package com.autocrypt.server1.pack1.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestClient

@Configuration
class RestClientConfig(
) {

    @Bean
    fun restClient(restClientBuilder:RestClient.Builder): RestClient {
        return restClientBuilder.build()
    }
}