package net.javaman.guavapicker.templates

import dev.kord.common.Color
import dev.kord.rest.builder.message.EmbedBuilder

object EmbedTemplate {
    operator fun invoke(header: String? = null, message: String? = null): EmbedBuilder.() -> Unit = {
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
}