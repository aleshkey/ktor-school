package example.com.service

import example.com.model.Student
import example.com.repository.impl.StudentRepository


class StudentService(private val studentRepository: StudentRepository) {

    suspend fun getAll(): List<Student> =
        studentRepository.findAll()


}
