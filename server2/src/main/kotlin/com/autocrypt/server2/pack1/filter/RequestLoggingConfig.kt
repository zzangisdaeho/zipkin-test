package com.autocrypt.server2.pack1.filter

import org.springframework.web.filter.CommonsRequestLoggingFilter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RequestLoggingConfig {

    @Bean
    fun logFilter(): CommonsRequestLoggingFilter {
        val filter = CommonsRequestLoggingFilter()
        filter.setIncludeQueryString(true)      // 쿼리 스트링 포함
        filter.setIncludePayload(true)         // 바디 포함
        filter.setIncludeHeaders(true)         // 헤더 포함
        filter.setMaxPayloadLength(10000)      // 최대 바디 크기
        return filter
    }
}