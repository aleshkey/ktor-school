package example.com.routing

import example.com.repository.impl.StudentRepository
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import example.com.service.StudentService

var studentRepository = StudentRepository()
var studentService = StudentService(studentRepository)

fun Application.studentRoutes () {
    routing {
        get ("api/v1/students") {
            val res = studentService.getAll()
            call.respond(res)
        }
    }
}
