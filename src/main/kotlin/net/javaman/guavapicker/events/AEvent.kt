package net.javaman.guavapicker.events

import dev.kord.core.behavior.interaction.response.respond
import dev.kord.core.event.interaction.ActionInteractionCreateEvent
import java.util.UUID
import mu.KotlinLogging
import net.javaman.guavapicker.Handler
import net.javaman.guavapicker.ResponseHandler
import net.javaman.guavapicker.templates.ErrorTemplate

abstract class AEvent<T : ActionInteractionCreateEvent> {
    private val logger = KotlinLogging.logger {}

    val handler: Handler<T> = {
        val response = interaction.deferEphemeralResponse()
        try {
            handlerImpl(response)
        } catch (e: Exception) {
            val uuid = UUID.randomUUID()
            response.respond(ErrorTemplate(e, uuid))
            logger.error(e) { "UUID: $uuid" }
        }
    }

    protected abstract val handlerImpl: ResponseHandler<T>
}
