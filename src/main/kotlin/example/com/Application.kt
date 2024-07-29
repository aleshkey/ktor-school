package example.com

import example.com.routing.streamRoutes
import example.com.routing.studentRoutes
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.application.*
import io.ktor.server.plugins.contentnegotiation.*

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    install(ContentNegotiation) {
        json()
    }
    example.com.config.ApplicationConfig(environment).configure()
    studentRoutes()
    streamRoutes()
}
