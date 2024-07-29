package example.com.handler.commands

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.KotlinModule
import example.com.routing.studentRepository
import example.com.service.StudentService

abstract class Command{

    protected val studentService = StudentService(studentRepository)

    protected val mapper = ObjectMapper().registerModule(KotlinModule())!!

    abstract fun execute(content: Any)
}
