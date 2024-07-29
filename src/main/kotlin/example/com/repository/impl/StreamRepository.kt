package example.com.repository.impl

import example.com.model.Stream
import example.com.config.DatabaseConfig

class StreamRepository {

    private val jdbi = DatabaseConfig.jdbi!!

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
                .findFirst()
                .orElse(null)
        }
    }

}
