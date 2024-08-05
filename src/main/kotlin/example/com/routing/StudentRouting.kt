package example.com.routing

import example.com.client.MockClient
import example.com.config.DatabaseConfig
import example.com.payload.rabbit.DeleteStudentMessage
import example.com.payload.rabbit.enums.MessageStatus
import example.com.payload.request.student.StudentRequest
import example.com.payload.response.MessageResponse
import example.com.repository.impl.StudentRepository
import example.com.service.RabbitService
import example.com.service.StudentService
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

var studentRepository = StudentRepository(DatabaseConfig.jdbi!!)
var studentService = StudentService(studentRepository)
var rabbitService = RabbitService()

fun Application.studentRoutes () {
    routing {
        route("api/v1/students") {
            get {
                val res = studentService.getAll()
                call.respond(res)
            }

            post {
                try {
                    val request = call.receive<StudentRequest>()
                    rabbitService.sendMessage(request, MessageStatus.CREATE)
                    val status = HttpStatusCode.Accepted
                    call.respond(status, MessageResponse(status.value, "Accepted!"))
                } catch (ex: Exception){
                    ex.printStackTrace()
                    call.respond(
                        HttpStatusCode.InternalServerError,
                        MessageResponse(
                            status = HttpStatusCode.InternalServerError.value,
                            message = ex.message.toString()
                        )
                    )
                }
            }

            get("/{id}") {
                val id = call.parameters["id"]!!.toLong()
                call.respond(studentService.getById(id))
            }

            delete ("/{id}") {
                val id = call.parameters["id"]!!.toLong()
                rabbitService.sendMessage(DeleteStudentMessage(id), MessageStatus.DELETE)
                call.respond(HttpStatusCode.NoContent)
            }

            get("/mock") {
                call.respond(MockClient().getResponse())
            }
        }
    }
}
