package com.autocrypt.server1

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableAsync

@SpringBootApplication
@EnableAsync
class Server1Application

fun main(args: Array<String>) {
    runApplication<Server1Application>(*args)
}
