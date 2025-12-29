package org.delcom

import io.ktor.server.application.*
import org.delcom.data.todoModule
import org.koin.ktor.plugin.Koin
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.ContentNegotiation
import io.ktor.server.plugins.cors.routing.CORS
import kotlinx.serialization.json.Json
import io.github.cdimascio.dotenv.dotenv
import io.ktor.server.engine.embeddedServer
import io.ktor.server.netty.Netty

fun main(args: Array<String>) {
    val dotenv = dotenv()

    dotenv.entries().forEach {
        System.setProperty(it.key, it.value)
    }

    val port = System.getProperty("APP_PORT").toInt()
    val host = System.getProperty("APP_HOST")
    embeddedServer(Netty, port = port, host = host) {
        module()
    }.start(wait = true)
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
