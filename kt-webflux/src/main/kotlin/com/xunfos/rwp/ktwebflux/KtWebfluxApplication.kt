package com.xunfos.rwp.ktwebflux

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class KtWebfluxApplication

fun main(args: Array<String>) {
    runApplication<KtWebfluxApplication>(*args)
}

@RestController
@RequestMapping("/hello")
class HelloController {
    @GetMapping("/block")
    fun normalHello(): String {
        trace("Running blocking Hello!")
        Thread.sleep(3_000)
        return "Hello!"
    }

    @GetMapping("/suspend")
    suspend fun suspendHello(): String {
        trace("Running suspend Hello!")
        delay(3_000)
        return "Hello after suspending!"
    }

    @GetMapping("/many")
    suspend fun bunchOfHellos(): Flow<String> = flow {
        repeat(10) {
            emit("Hello #$it")
            delay(500)
        }
    }
}

fun trace(msg: Any) = println("[${getThreadName()}] $msg")
fun getThreadName() = Thread.currentThread().name

