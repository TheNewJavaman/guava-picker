package net.javaman.guavapicker.discord.templates

import dev.kord.common.entity.ButtonStyle
import dev.kord.core.builder.components.emoji
import dev.kord.core.entity.ReactionEmoji
import dev.kord.rest.builder.message.create.MessageCreateBuilder
import dev.kord.rest.builder.message.create.actionRow
import dev.kord.rest.builder.message.create.embed
import net.javaman.guavapicker.discord.embedColor
import net.javaman.guavapicker.discord.interactions.RoleToggleButtonInteraction
import net.javaman.guavapicker.dynamodb.items.RoleToggleMessage

fun roleToggleMessageTemplate(roleToggleMessage: RoleToggleMessage): MessageCreateBuilder.() -> Unit = {
    if (roleToggleMessage.header != null || roleToggleMessage.body != null) {
        embed {
            color = embedColor
            roleToggleMessage.header?.let {
                author {
                    name = it
                }
            }
            roleToggleMessage.body?.let {
                description = it
            }
        }
    }
    if (roleToggleMessage.buttons.isNotEmpty()) {
        actionRow {
            for (button in roleToggleMessage.buttons) {
                interactionButton(
                    ButtonStyle.Secondary,
                    "${RoleToggleButtonInteraction.ID}-${button.roleId.value}"
                ) {
                    button.emoji?.let {
                        emoji(ReactionEmoji.Unicode(it))
                    }
                    label = button.label
                }
            }
        }
    }
}
