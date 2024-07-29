package example.com.payload.rabbit.enums

import kotlinx.serialization.Serializable

@Serializable
enum class MessageStatus {
    CREATE,
    DELETE
}
