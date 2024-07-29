package example.com.handler.commands.impl

import example.com.handler.commands.Command
import example.com.payload.request.student.StudentRequest

class CreateCommand : Command() {
    override fun execute(content: Any) {
        val studentRequest = mapper.convertValue(content, StudentRequest::class.java);
        studentService.saveStudent(studentRequest)

    }
}
