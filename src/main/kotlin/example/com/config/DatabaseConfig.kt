package example.com.config

import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import io.ktor.server.config.ApplicationConfig
import org.jdbi.v3.core.Jdbi

object DatabaseConfig {
    private lateinit var config: ApplicationConfig

    fun init(environment: ApplicationEnvironment) {
        config = environment.config
        val dbConfig = HikariConfig().apply {
            jdbcUrl = config.property("ktor.database.jdbcUrl").getString()
            username = config.property("ktor.database.username").getString()
            password = config.property("ktor.database.password").getString()
            driverClassName = config.property("ktor.database.driverClassName").getString()
        }
        val dataSource = HikariDataSource(dbConfig)
        jdbi = Jdbi.create(dataSource)
        jdbi!!.installPlugins()
    }

    var jdbi: Jdbi? = null
}
