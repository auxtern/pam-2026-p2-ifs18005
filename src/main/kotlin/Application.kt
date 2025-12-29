package org.delcom

import io.ktor.server.application.*
import org.delcom.data.todoModule
import org.koin.ktor.plugin.Koin
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.cors.routing.CORS
import kotlinx.serialization.json.Json

fun main(args: Array<String>) {
    io.ktor.server.netty.EngineMain.main(args)
}

fun Application.module() {

    install(CORS) {
        anyHost()
    }

    install(ContentNegotiation) {
        json(
            Json {
                prettyPrint = true
                ignoreUnknownKeys = true
            }
        )
    }

    install(Koin) {
        modules(todoModule)
    }

    configureRouting()
}
