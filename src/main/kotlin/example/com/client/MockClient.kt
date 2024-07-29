package example.com.client

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*


class MockClient {
    private val client = HttpClient(CIO) {
        install(Logging) {
            level = LogLevel.INFO
        }
    }

    suspend fun getResponse(): List<MockStudent> {
        val response = client.get("https://jsonplaceholder.typicode.com/users").bodyAsText()
        val res: List<MockStudent> = ObjectMapper().readValue(response)
        return res
    }
}
