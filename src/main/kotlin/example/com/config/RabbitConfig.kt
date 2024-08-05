package example.com.config

import com.rabbitmq.client.ConnectionFactory
import example.com.constants.RabbitConstants

object RabbitConfig {

    private val connectionFactory: ConnectionFactory = ConnectionFactory()

    init {
        connectionFactory.host = "localhost"
        connectionFactory.port = 5672
        connectionFactory.virtualHost = "/"
        connectionFactory.username = "guest"
        connectionFactory.password = "guest"
    }

    fun defaultExchangeAndQueue() : RabbitConfig {
        val newConnection = getFactory().newConnection()
        val channel = newConnection.createChannel()
        channel.exchangeDeclare(
            RabbitConstants.DEFAULT_EXCHANGE,
            "direct",
            true
        )
        channel.queueDeclare(
            RabbitConstants.DEFAULT_QUEUE,
            true,
            false,
            true,
            emptyMap()
        )
        channel.queueBind(
            RabbitConstants.DEFAULT_QUEUE,
            RabbitConstants.DEFAULT_EXCHANGE,
            RabbitConstants.DEFAULT_KEY
        )
        channel.close()
        newConnection.close()
        return this
    }

    fun getFactory(): ConnectionFactory {
        return connectionFactory
    }
}
