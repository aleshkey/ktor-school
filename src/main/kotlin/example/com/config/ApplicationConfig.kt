package example.com.config

import example.com.service.RabbitService
import io.ktor.server.application.*

class ApplicationConfig(
    private var environment: ApplicationEnvironment
) {
    fun configure(){
        DatabaseConfig.init(environment)
        RabbitConfig.defaultExchangeAndQueue()
        RabbitService().startListening()
    }
}
