package com.autocrypt.server1.pack1.config

import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping

@FeignClient(name = "server2", url = "http://localhost:8081")
interface Server2FeignClient {

    @GetMapping("server2-endpoint")
    fun callServer2():String
}