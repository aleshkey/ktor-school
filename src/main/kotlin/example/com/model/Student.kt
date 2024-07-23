package example.com.model

import example.com.model.Stream
import kotlinx.serialization.Serializable

@Serializable
data class Student(
    val id: Long,
    val firstName: String,
    val lastName: String,
    val email: String,
    val age: Int,
    val stream: Stream
)
