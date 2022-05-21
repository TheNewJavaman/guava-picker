package net.javaman.guavapicker.discord.templates

import dev.kord.rest.builder.message.create.MessageCreateBuilder
import dev.kord.rest.builder.message.create.embed
import net.javaman.guavapicker.discord.embedColor

fun errorTemplate(e: Exception): MessageCreateBuilder.() -> Unit = {
    embed {
        color = embedColor
        author {
            name = "Something went wrong"
        }
        description = e.message
    }
}
