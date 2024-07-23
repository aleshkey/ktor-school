package example.com.model

import kotlinx.serialization.Serializable

@Serializable
data class Stream(
    val id: Int,
    val name: String,
    val students: List<Student> = emptyList()
)
