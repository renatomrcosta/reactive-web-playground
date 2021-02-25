package com.xunfos.rwp.ktwebflux

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withTimeout
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.reactive.function.client.awaitBody

class BreakMVCTest {
    @Test
    fun `should call the MVC suspended method a buncha times`() {
        val webClient: WebClient = WebClient.builder().baseUrl("http://localhost:8081/hello/suspend").build()

        runBlocking(Dispatchers.IO) {
            // 3s to reply x 150 calls using 10 threads
            repeat(150) {
                launch {
                    webClient.get()
                        .retrieve()
                        .awaitBody<String>()
                        .run { println(this) }
                }
            }
        }
    }
}
