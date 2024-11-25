package com.autocrypt.server1.pack1.service

import com.autocrypt.server1.pack1.config.Server2FeignClient
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.web.client.RestClient
import org.springframework.web.client.RestTemplate

@Service
class OuterService(
    private val restTemplate: RestTemplate,
    private val server2FeignClient: Server2FeignClient,
    private val restClient: RestClient
) {

    private val log = LoggerFactory.getLogger(OuterService::class.java)

    fun callServer2WithRestTemplate(): String {
        log.info("Calling Server2 via RestTemplate")
        val url = "http://localhost:8081/server2-endpoint" // 서버2번의 엔드포인트
        val response = restTemplate.getForObject(url, String::class.java)
        log.info("Response from Server2: {}", response)
        return response ?: "No response from Server2"
    }

    fun callServer2WithFeign(): String {
        val response = server2FeignClient.callServer2()
        log.info("Response from Server2: {}", response)
        return response ?: "No response from Server2"
    }

    fun callServer2WithRestClient(): String {
        log.info("Calling Service2 from Service1 with RestClient")
        val response = restClient.get()
            .uri("http://localhost:8081/server2-endpoint")
            .retrieve()
            .body(String::class.java)
        log.info("Response from Service2: {}", response)
        return response?:"No response from Server2"
    }


}