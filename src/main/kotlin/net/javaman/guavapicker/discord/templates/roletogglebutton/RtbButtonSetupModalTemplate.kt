package net.javaman.guavapicker.discord.templates.roletogglebutton

import dev.kord.common.entity.TextInputStyle
import dev.kord.core.entity.Role
import dev.kord.rest.builder.interaction.ModalBuilder
import net.javaman.guavapicker.discord.interactions.roletogglebutton.RtbAddRoleModalInteraction

fun rtbButtonSetupModalTemplate(role: Role): ModalBuilder.() -> Unit = {
    actionRow {
        textInput(
            TextInputStyle.Short,
            RtbAddRoleModalInteraction.EMOJI_ID,
            "Emoji (optional, unicode only, one max)"
        ) {
            required = false
            role.unicodeEmoji?.let {
                value = it
            }
        }
        textInput(TextInputStyle.Short, RtbAddRoleModalInteraction.LABEL_ID, "Label") {
            required = true
            value = role.name
        }
    }
}
