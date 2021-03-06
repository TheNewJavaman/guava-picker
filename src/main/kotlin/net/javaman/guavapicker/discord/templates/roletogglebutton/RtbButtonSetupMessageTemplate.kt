package net.javaman.guavapicker.discord.templates.roletogglebutton

import dev.kord.common.entity.DiscordPartialEmoji
import dev.kord.core.entity.Role
import dev.kord.rest.builder.message.create.InteractionResponseCreateBuilder
import dev.kord.rest.builder.message.create.actionRow
import dev.kord.rest.builder.message.create.embed
import net.javaman.guavapicker.discord.embedColor
import net.javaman.guavapicker.discord.interactions.roletogglebutton.RtbAddRoleMessageInteraction

fun rtbButtonSetupMessageTemplate(messageId: ULong, roles: List<Role>): InteractionResponseCreateBuilder.() -> Unit = {
    embed {
        color = embedColor
        author {
            name = "Add a role toggle button"
        }
    }
    actionRow {
        selectMenu("${RtbAddRoleMessageInteraction.ID}-$messageId") {
            placeholder = "Select a role"
            for (role in roles) {
                option(role.name, role.id.value.toString()) {
                    role.unicodeEmoji?.let {
                        emoji = DiscordPartialEmoji(name = it)
                    }
                }
            }
        }
    }
}
