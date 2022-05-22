package net.javaman.guavapicker.discord.templates.roletogglebutton

import dev.kord.common.entity.TextInputStyle
import dev.kord.rest.builder.interaction.ModalBuilder
import net.javaman.guavapicker.discord.interactions.roletogglebutton.RtbCreateModalInteraction

fun rtbMessageSetupModalTemplate(): ModalBuilder.() -> Unit = {
    actionRow {
        textInput(TextInputStyle.Short, RtbCreateModalInteraction.HEADER_ID, "Header (optional)") {
            required = false
        }
    }
    actionRow {
        textInput(TextInputStyle.Paragraph, RtbCreateModalInteraction.BODY_ID, "Body (optional)") {
            required = false
        }
    }
}
