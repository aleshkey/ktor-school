package example.com.payload.response

import kotlinx.serialization.Serializable

@Serializable
data class MessageResponse(
    val status: Int,
    val message: String
)
