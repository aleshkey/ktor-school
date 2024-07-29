package example.com.routing

import example.com.repository.impl.StreamRepository
import example.com.service.StreamService
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

val streamRepository = StreamRepository()
val streamService = StreamService(streamRepository)

fun Application.streamRoutes () {
    routing {
        route("api/v1/streams") {
            get {
                val res = streamService.getAll()
                call.respond(res)
            }

            get("/{id}") {
                val id = call.parameters["id"]!!.toLong()
                call.respond(streamService.getById(id)!!)
            }
        }
    }
}
