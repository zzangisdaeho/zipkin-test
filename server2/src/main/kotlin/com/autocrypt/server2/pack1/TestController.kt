package com.autocrypt.server2.pack1

import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class TestController(
) {

    val log = LoggerFactory.getLogger(this.javaClass)

    @GetMapping("server2-endpoint")
    fun answer() : String{
        log.info("server2 receive")
        return "Acknowledgement"
    }

}