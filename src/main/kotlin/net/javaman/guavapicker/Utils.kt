package net.javaman.guavapicker

import dev.kord.common.Color
import dev.kord.core.Kord
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent
import dev.kord.rest.builder.message.EmbedBuilder
import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

suspend fun startDiscord(): Kord {
    logger.info { "Starting Discord client" }
    return Kord(System.getenv("GUAVA_DISCORD_TOKEN"))
}

suspend fun logIntoDiscord(kord: Kord) {
    logger.info { "Logging in Discord client" }
    kord.login {
        @OptIn(PrivilegedIntent::class)
        intents += Intent.MessageContent
    }
}

fun embedTemplate(header: String? = null, message: String? = null): EmbedBuilder.() -> Unit = {
    color = Color(209, 96, 86)
    header?.let {
        author {
            name = it
        }
    }
    message?.let {
        description = it
    }
}
