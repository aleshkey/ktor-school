package example.com.payload.response.stream

import kotlinx.serialization.Serializable

@Serializable
data class StreamResponse (
    val size: Long,
    val name: String,
    val students: List<String>,
)
