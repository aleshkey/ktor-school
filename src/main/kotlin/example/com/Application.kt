package example.com

import example.com.routing.studentRoutes
import io.ktor.server.application.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    example.com.config.ApplicationConfig(environment).configure()
    studentRoutes()
}
