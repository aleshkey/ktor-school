package example.com.payload.rabbit

import example.com.payload.rabbit.enums.MessageStatus
import kotlinx.serialization.Serializable

@Serializable
data class RabbitMessage<T> (
    val status: MessageStatus,
    val content: T
)
