package example.com.repository.impl

import example.com.model.Stream
import example.com.payload.response.stream.StreamListResponse
import example.com.payload.response.stream.StreamResponse
import org.jdbi.v3.core.Jdbi

class StreamRepository (
    private val jdbi: Jdbi
) {

    fun getById(id: Long): List<StreamResponse>? {
        return jdbi.withHandle<List<StreamResponse>?, Exception> { handle ->
            handle.select("""
            SELECT s.name as stream_name, COUNT(st.id) as size, JSON_AGG(st.last_name) as student_names
            FROM streams s
            LEFT JOIN students st ON s.id = st.stream_id
            WHERE s.id = :id
            GROUP BY s.name;
        """)
                .bind("id", id)
                .mapTo(StreamResponse::class.java)
                .list()
        }
    }

    fun getAll(): List<StreamListResponse> {
        return jdbi.withHandle<List<StreamListResponse>?, Exception>{ handle ->
            handle.createQuery("""
                SELECT s.id as stream_id, s.name, COUNT(s2.id) as student_count
                FROM streams s
                         LEFT JOIN students s2 ON s.id = s2.stream_id
                GROUP BY s.id, s.name
            """)
                .map{ rs, _, _, ->
                    StreamListResponse(
                        id = rs.getLong("stream_id"),
                        name = rs.getString("name"),
                        studentCount = rs.getInt("student_count")
                    )
                }
                .list()
        }
    }

    fun findByName(name: String): Stream? {
        return jdbi.withHandle<Stream?, Exception> { handle ->
            handle.createQuery("""
                SELECT * FROM streams WHERE name = :name
            """)
                .bind("name", name)
                .map { rs, _, _ ->
                    Stream(
                        id = rs.getInt("id"),
                        name = rs.getString("name")
                    )
                }
                .firstOrNull()
        }
    }

}
