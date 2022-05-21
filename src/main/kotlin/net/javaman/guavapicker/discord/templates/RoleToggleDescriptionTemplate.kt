package net.javaman.guavapicker.discord.templates

import dev.kord.common.entity.TextInputStyle
import dev.kord.rest.builder.interaction.ModalBuilder
import net.javaman.guavapicker.discord.interactions.CreateDescriptionInteraction

fun roleToggleDescriptionTemplate(): ModalBuilder.() -> Unit = {
    actionRow {
        textInput(TextInputStyle.Short, CreateDescriptionInteraction.HEADER_ID, "Header (optional)") {
            required = false
        }
    }
    actionRow {
        textInput(TextInputStyle.Paragraph, CreateDescriptionInteraction.BODY_ID, "Body (optional)") {
            required = false
        }
    }
}
