package com.autocrypt.server1.pack1.config

import io.micrometer.context.ContextExecutorService
import io.micrometer.context.ContextSnapshotFactory
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor
import java.util.concurrent.Executor

@Configuration
class AsyncConfig {

    @Bean
    fun taskExecutor(): Executor {
        // ThreadPoolTaskExecutor 설정
        val threadPoolTaskExecutor = ThreadPoolTaskExecutor()
        threadPoolTaskExecutor.corePoolSize = 5
        threadPoolTaskExecutor.maxPoolSize = 10
        threadPoolTaskExecutor.setQueueCapacity(25)
        threadPoolTaskExecutor.setThreadNamePrefix("async-task-")
        threadPoolTaskExecutor.initialize()

        // 최신 ContextSnapshotFactory를 사용한 Wrapping
        val executorService = threadPoolTaskExecutor.threadPoolExecutor
        val contextSnapshotFactory = ContextSnapshotFactory.builder().build()

        return ContextExecutorService.wrap(
            executorService
        ) { contextSnapshotFactory.captureAll() } // 최신 방식으로 ContextSnapshot 캡처
    }

}