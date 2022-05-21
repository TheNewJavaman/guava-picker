package net.javaman.guavapicker.discord.templates

import dev.kord.common.entity.TextInputStyle
import dev.kord.rest.builder.interaction.ModalBuilder

object DescriptionModalTemplate {
    const val HEADER_ID = "header"
    const val BODY_ID = "body"

    operator fun invoke(): ModalBuilder.() -> Unit = {
        actionRow {
            textInput(TextInputStyle.Short, HEADER_ID, "Header (optional)")
        }
        actionRow {
            textInput(TextInputStyle.Paragraph, BODY_ID, "Body (optional)")
        }
    }
}