package example.com.model

import example.com.payload.response.student.StudentResponse
import kotlinx.serialization.Serializable

@Serializable
data class Student(
    val id: Long?,
    val firstName: String,
    val lastName: String,
    val email: String,
    val age: Int,
    val stream: Stream?
){
    fun toResponse(): StudentResponse {
        return StudentResponse(
            firstName = this.firstName,
            lastName = this.lastName,
            email = this.email,
            age = this.age,
            streamName = stream!!.name,
            id = this.id!!
        )
    }
}
