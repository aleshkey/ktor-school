package example.com

import example.com.routing.studentRoutes
import io.ktor.server.application.*
import org.modsen.config.DatabaseConfig

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {
    DatabaseConfig.init(environment)

    studentRoutes()
}
