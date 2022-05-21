package net.javaman.guavapicker.discord

import dev.kord.common.Color
import dev.kord.core.Kord
import dev.kord.core.behavior.interaction.respondEphemeral
import dev.kord.core.event.interaction.ActionInteractionCreateEvent
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent
import java.util.UUID
import mu.KotlinLogging
import net.javaman.guavapicker.discord.templates.errorTemplate

private val logger = KotlinLogging.logger {}

suspend fun startDiscord(builder: suspend Kord.() -> Unit) {
    val kord = Kord(System.getenv("GUAVA_DISCORD_TOKEN"))
    logger.info { "Starting Discord client" }
    kord.builder()
    logger.info { "Logging in Discord client" }
    kord.login {
        @OptIn(PrivilegedIntent::class)
        intents += Intent.MessageContent
    }
}

val embedColor = Color(209, 96, 86)

typealias Handler<T> = suspend T.() -> Unit

suspend fun <T : ActionInteractionCreateEvent> T.handlingExceptions(block: Handler<T>) {
    try {
        block()
    } catch (e: Exception) {
        val uuid = UUID.randomUUID()
        logger.error(e) { "UUID: $uuid" }
        interaction.respondEphemeral(errorTemplate(e))
    }
}