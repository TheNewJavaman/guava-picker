package net.javaman.guavapicker.discord

import dev.kord.common.Color
import dev.kord.core.behavior.interaction.respondEphemeral
import dev.kord.core.event.interaction.ActionInteractionCreateEvent
import dev.kord.rest.builder.message.create.embed
import java.util.UUID
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

val embedColor = Color(209, 96, 86)

typealias Handler<T> = suspend T.() -> Unit

suspend fun <T : ActionInteractionCreateEvent> T.handlingExceptions(block: Handler<T>): Handler<T> = {
    try {
        block()
    } catch (e: Exception) {
        val uuid = UUID.randomUUID()
        interaction.respondEphemeral {
            embed {
                color = embedColor
                author {
                    name = "Something went wrong"
                }
                description = e.message
            }
        }
        logger.error(e) { "UUID: $uuid" }
    }
}