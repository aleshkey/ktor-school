package example.com.payload.response.stream

import kotlinx.serialization.Serializable

@Serializable
data class StreamListResponse(
    val id: Long,
    val name: String,
    val studentCount: Int
)
