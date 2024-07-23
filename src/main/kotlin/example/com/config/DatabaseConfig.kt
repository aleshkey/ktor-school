package org.modsen.config

import com.typesafe.config.ConfigFactory
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.application.*
import io.ktor.server.config.*
import org.jdbi.v3.core.Jdbi
import io.ktor.server.application.ApplicationEnvironment
import io.ktor.server.config.ApplicationConfig

object DatabaseConfig {
    private var config: ApplicationConfig? = null

    fun init(environment: ApplicationEnvironment) {
        config = environment.config
        val dbConfig = HikariConfig().apply {
            jdbcUrl = config!!.property("ktor.database.jdbcUrl").getString()
            username = config!!.property("ktor.database.username").getString()
            password = config!!.property("ktor.database.password").getString()
            driverClassName = config!!.property("ktor.database.driverClassName").getString()
        }
        val dataSource = HikariDataSource(dbConfig)
        DatabaseConfig.jdbi = Jdbi.create(dataSource)
    }

    var jdbi: Jdbi? = null
}
