package example.com.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import com.fasterxml.jackson.module.kotlin.readValue
import com.rabbitmq.client.CancelCallback
import com.rabbitmq.client.DeliverCallback
import com.rabbitmq.client.Delivery
import example.com.config.RabbitConfig
import example.com.constants.RabbitConstants
import example.com.handler.RabbitMessageHandler
import example.com.payload.rabbit.RabbitMessage
import example.com.payload.rabbit.enums.MessageStatus
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class RabbitService {
    private val mapper = ObjectMapper().registerModule(KotlinModule())

    fun <T> sendMessage(obj: T, status: MessageStatus) {
        val connection = RabbitConfig.getFactory().newConnection()
        val channel = connection.createChannel()

        val message: RabbitMessage<T> = RabbitMessage(
            status = status,
            content = obj
        )
        channel.basicPublish(
            RabbitConstants.DEFAULT_EXCHANGE,
            RabbitConstants.DEFAULT_KEY,
            null,
            mapper.writeValueAsBytes(message)
        )

        channel.close()
        connection.close()
    }

    fun startListening() {
        GlobalScope.launch {
            val connection = RabbitConfig.getFactory().newConnection()
            val channel = connection.createChannel()
            val handler = RabbitMessageHandler()

            val deliverCallback = DeliverCallback { _: String?, message: Delivery? ->

                try {
                    val jsonString = String(message!!.body)
                    val obj: RabbitMessage<Any> = mapper.readValue(jsonString)
                    handler.handle(obj)
                } catch (e: Exception) {
                    println("Failed to deserialize message: ${e.message}")
                }
            }
            val cancelCallback = CancelCallback { consumerTag: String? -> println("Cancelled... $consumerTag") }

            channel.basicConsume(
                RabbitConstants.DEFAULT_QUEUE,
                false,
                deliverCallback,
                cancelCallback
            )
        }
    }
}
