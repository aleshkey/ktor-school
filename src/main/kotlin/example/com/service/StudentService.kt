package example.com.service

import example.com.payload.request.student.StudentRequest
import example.com.payload.response.student.StudentResponse
import example.com.repository.impl.StudentRepository


class StudentService(private val studentRepository: StudentRepository) {

    fun getAll(): List<StudentResponse> =
        studentRepository.findAll().map { it.toResponse() }

    fun saveStudent(studentRequest: StudentRequest){
        val student = studentRequest.toObj()
        studentRepository.save(student)
    }

    fun deleteStudent(id: Long){
        studentRepository.deleteById(id)
    }

    fun getById(id: Long): StudentResponse {
        return studentRepository.findById(id)!!.toResponse()
    }

}
