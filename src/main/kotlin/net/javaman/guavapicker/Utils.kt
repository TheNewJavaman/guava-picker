package net.javaman.guavapicker

import dev.kord.core.Kord
import dev.kord.core.behavior.interaction.response.DeferredEphemeralMessageInteractionResponseBehavior
import dev.kord.core.behavior.interaction.response.respond
import dev.kord.core.event.interaction.ActionInteractionCreateEvent
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent
import java.util.UUID
import kotlinx.serialization.json.Json
import mu.KotlinLogging
import net.javaman.guavapicker.templates.ErrorTemplate

val serializer = Json { ignoreUnknownKeys = true }

private val logger = KotlinLogging.logger {}

suspend fun startDiscord(builder: suspend Kord.() -> Unit) = with(Kord(System.getenv("GUAVA_DISCORD_TOKEN"))) {
    logger.info { "Starting Discord client" }
    builder()
    logger.info { "Logging in Discord client" }
    login {
        @OptIn(PrivilegedIntent::class)
        intents += Intent.MessageContent
    }
}

fun <T : ActionInteractionCreateEvent> T.deferredEphemeralResponse(block: ResponseHandler<T>): Handler<T> = {
    val response = interaction.deferEphemeralResponse()
    try {
        block(response)
    } catch (e: Exception) {
        val uuid = UUID.randomUUID()
        response.respond(ErrorTemplate(e, uuid))
        logger.error(e) { "UUID: $uuid" }
    }
}

class GuavaPickerException(message: String) : Exception(message)

typealias Handler<T> = suspend T.() -> Unit

typealias ResponseHandler<T> = suspend T.(DeferredEphemeralMessageInteractionResponseBehavior) -> Unit
