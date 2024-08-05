package example.com.repository.impl

import example.com.model.Stream
import example.com.model.Student
import example.com.repository.CrudRepository
import org.jdbi.v3.core.Jdbi
import org.jdbi.v3.core.kotlin.bindKotlin

class StudentRepository(
    private val jdbi: Jdbi
): CrudRepository<Student, Long> {



    override fun findAll(): List<Student> {
        return jdbi.withHandle<List<Student>, Exception> { handle ->
            val query = """
                SELECT s.id AS student_id, s.first_name, s.last_name, s.email, s.age,
                       st.id AS stream_id, st.name AS stream_name
                FROM students s
                JOIN streams st ON s.stream_id = st.id
            """
            handle.createQuery(query)
                .map { rs, _, _ ->
                    val stream = Stream(
                        id = rs.getInt("stream_id"),
                        name = rs.getString("stream_name")
                    )
                    Student(
                        id = rs.getLong("student_id"),
                        firstName = rs.getString("first_name"),
                        lastName = rs.getString("last_name"),
                        email = rs.getString("email"),
                        age = rs.getInt("age"),
                        stream = stream
                    )
                }
                .list()
        }
    }

    override fun update(entity: Student): Student {
        jdbi.useHandle<Exception> { handle ->
            handle.createUpdate("""
                UPDATE students
                SET first_name = :firstName,
                    last_name = :lastName,
                    email = :email,
                    age = :age,
                    stream_id = :streamId
                WHERE id = :id
            """)
                .bindKotlin(entity)
                .execute()
        }
        return entity
    }

    override fun deleteById(id: Long) {
        jdbi.useHandle<Exception> { handle ->
            handle.createUpdate("DELETE FROM students WHERE id = :id")
                .bind("id", id)
                .execute()
        }
    }

    override fun save(entity: Student): Student {
        jdbi.useHandle<Exception> { handle ->
            handle.createUpdate("""
                INSERT INTO students (first_name, last_name, email, age, stream_id)
                VALUES (:firstName, :lastName, :email, :age, :stream)
            """)
                .bindKotlin(entity)
                .execute()
        }
        return entity
    }

    override fun findById(id: Long): Student? {
        return jdbi.withHandle<Student?, Exception> { handle ->
            val query = """
                SELECT s.id AS student_id, s.first_name, s.last_name, s.email, s.age,
                       st.id AS stream_id, st.name AS stream_name
                FROM students s
                JOIN streams st ON s.stream_id = st.id
                WHERE s.id = :id
            """
            handle.createQuery(query)
                .bind("id", id)
                .map { rs, _, _ ->
                    val stream = Stream(
                        id = rs.getInt("stream_id"),
                        name = rs.getString("stream_name")
                    )
                    Student(
                        id = rs.getLong("student_id"),
                        firstName = rs.getString("first_name"),
                        lastName = rs.getString("last_name"),
                        email = rs.getString("email"),
                        age = rs.getInt("age"),
                        stream = stream
                    )
                }
                .firstOrNull()
        }
    }
}
