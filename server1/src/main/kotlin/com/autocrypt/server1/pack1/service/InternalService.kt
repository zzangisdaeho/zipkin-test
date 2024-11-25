package com.autocrypt.server1.pack1.service

import brave.Tracer
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate

@Service
class InternalService(
    private val tracer: Tracer,

) {

    private val log = LoggerFactory.getLogger(this.javaClass)

    fun normalTestServiceMethod() : String{
        log.info("normalTestServiceMethod()")
        return "Service";
    }



    fun failTestServiceMethod() : String{
        try{
            throw IllegalArgumentException("failTestServiceMethod()")
        }catch(e: Exception){
            tracer.currentSpan()?.tag("errorMSG", e.stackTraceToString())
            tracer.currentSpan()?.annotate(e.stackTraceToString())
            log.error("failTestServiceMethod now fail", e)
            throw e
        }
    }

    @Async
    fun asyncMethod() {
        log.info("asyncMethod() started")
        Thread.sleep(1000) // 작업 시뮬레이션
        log.info("asyncMethod() completed")
    }


    @Async
    fun asyncMethod2() {
//        val parentSpan = tracer.currentSpan() // 현재 Span 가져오기
//        val parentContext = parentSpan?.context() // TraceContext 가져오기

        val childSpan = tracer.nextSpan();
//        // 부모 Span이 존재할 경우 새로운 자식 Span 생성
//        val childSpan = if (parentContext != null) {
//            tracer.nextSpan()
////            tracer.nextSpan(TraceContextOrSamplingFlags.create(parentContext)).name("service-asyncMethod2-span").start()
//        } else {
//            tracer.nextSpan().name("service-asyncMethod2-span").start() // 부모가 없으면 새 Trace 시작
//        }

        try {
            tracer.withSpanInScope(childSpan).use {
                log.info("Service: New child span ID: {}", childSpan.context().spanIdString())
                log.info("Service: asyncMethod2() started")
                Thread.sleep(1000) // 작업 시뮬레이션
                log.info("Service: asyncMethod2() completed")
            }
        } finally {
            childSpan.finish() // Span 종료
        }
    }


}