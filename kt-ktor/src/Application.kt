package com.xunfox.rwp

import io.ktor.application.*
import io.ktor.response.*
import io.ktor.routing.get
import io.ktor.routing.route
import io.ktor.routing.routing
import kotlinx.coroutines.delay

fun main(args: Array<String>): Unit = io.ktor.server.netty.EngineMain.main(args)

@Suppress("unused") // Referenced in application.conf
@kotlin.jvm.JvmOverloads
fun Application.module(testing: Boolean = false) {
    routing {
        route("/hello") {
            get {
                trace("Calling suspend hello!")
                delay(1000)
                call.respond("Hello!")
            }
        }
    }
}

fun trace(msg: Any) = println("[${getThreadName()}] $msg")
fun getThreadName() = Thread.currentThread().name

