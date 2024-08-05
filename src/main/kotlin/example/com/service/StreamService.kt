package example.com.service

import example.com.payload.response.stream.StreamListResponse
import example.com.payload.response.stream.StreamResponse
import example.com.repository.impl.StreamRepository

class StreamService (
    private val streamRepository: StreamRepository
) {

    fun getAll(): List<StreamListResponse> {
        return streamRepository.getAll()
    }

    fun getById(id: Long): List<StreamResponse>? {
        return streamRepository.getById(id)
    }

}
