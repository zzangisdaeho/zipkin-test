package com.autocrypt.server1.pack1.controller

import com.autocrypt.server1.pack1.service.InternalService
import com.autocrypt.server1.pack1.service.OuterService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("internal")
class InternalController(
    private val internalService: InternalService,
) {

    val log = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("normal")
    fun normalTest(): String {
        log.info("normalTest controller listen")
        return internalService.normalTestServiceMethod()
    }

    @GetMapping("fail")
    fun failTest(): String {
        log.info("failTest controller listen")
        return internalService.failTestServiceMethod()
    }

    @GetMapping("async")
    fun asyncTest(): String {
        log.info("asyncTest controller listen")
        internalService.asyncMethod() // 비동기 호출
        return "Async Test Triggered"
    }

    @GetMapping("async2")
    fun asyncTest2(): String {
        log.info("asyncTest2 controller listen")
        internalService.asyncMethod2() // 비동기 호출
        return "Async Test2 Triggered"
    }

}