package net.javaman.guavapicker.discord.templates

import dev.kord.common.entity.TextInputStyle
import dev.kord.core.entity.Role
import dev.kord.rest.builder.interaction.ModalBuilder
import net.javaman.guavapicker.discord.interactions.AddRoleSubmitInteraction

fun roleToggleLabelTemplate(role: Role): ModalBuilder.() -> Unit = {
    actionRow {
        textInput(
            TextInputStyle.Short,
            AddRoleSubmitInteraction.EMOJI_ID,
            "Emoji (optional, unicode only, one max)"
        ) {
            required = false
            role.unicodeEmoji?.let {
                value = it
            }
        }
        textInput(TextInputStyle.Short, AddRoleSubmitInteraction.LABEL_ID, "Label") {
            required = true
            value = role.name
        }
    }
}
