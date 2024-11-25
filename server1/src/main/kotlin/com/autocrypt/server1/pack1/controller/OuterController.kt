package com.autocrypt.server1.pack1.controller

import com.autocrypt.server1.pack1.service.InternalService
import com.autocrypt.server1.pack1.service.OuterService
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("outer")
class OuterController(
    private val outerService: OuterService
) {

    val log = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("restTemplate")
    fun callOtherServerWithRestTemplate(): String{
        log.info("call-other server by restTemplate controller listen")
        return outerService.callServer2WithRestTemplate()
    }

    @GetMapping("feign")
    fun callOtherServerWithFeignClient(): String{
        log.info("call-other server by feign controller listen")
        return outerService.callServer2WithFeign()
    }

    @GetMapping("restClient")
    fun callOtherServerWithRestClient(): String{
        log.info("call-other server by restClient controller listen")
        return outerService.callServer2WithRestClient()
    }


}