package com.xunfos.rwp.ktwebmvc

import kotlinx.coroutines.delay
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class KtWebmvcApplication

fun main(args: Array<String>) {
	runApplication<KtWebmvcApplication>(*args)
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
}

fun trace(msg: Any) = println("[${getThreadName()}] $msg")
fun getThreadName() = Thread.currentThread().name
