package example.com.payload.request.student

import example.com.config.DatabaseConfig
import example.com.model.Student
import example.com.repository.impl.StreamRepository
import kotlinx.serialization.Serializable

@Serializable
data class StudentRequest(
    val firstName: String,
    val lastName: String,
    val email: String,
    val age: Int,
    val stream: String
){
    fun toObj() : Student {
        return Student(
            id = null,
            firstName = this.firstName,
            lastName = this.lastName,
            email = this.email,
            age = this.age,
            stream = StreamRepository(DatabaseConfig.jdbi!!).findByName(stream)
        )
    }
}
