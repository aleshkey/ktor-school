package example.com.handler.commands.impl

import example.com.handler.commands.Command
import example.com.payload.rabbit.DeleteStudentMessage

class DeleteCommand : Command() {
    override fun execute(content: Any) {
        val message = mapper.convertValue(content, DeleteStudentMessage::class.java);
        studentService.deleteStudent(message.id)
    }
}
