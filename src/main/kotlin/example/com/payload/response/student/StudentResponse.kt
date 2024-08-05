package example.com.payload.response.student

import kotlinx.serialization.Serializable

@Serializable
data class StudentResponse (
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val age: Int,
    val streamName: String
)
