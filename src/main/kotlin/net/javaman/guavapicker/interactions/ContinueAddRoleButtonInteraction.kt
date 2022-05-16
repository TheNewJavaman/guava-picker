package net.javaman.guavapicker.interactions

import dev.kord.common.entity.Snowflake
import dev.kord.common.entity.TextInputStyle
import dev.kord.core.behavior.interaction.modal
import dev.kord.core.event.interaction.ButtonInteractionCreateEvent
import net.javaman.guavapicker.GuavaPickerException
import net.javaman.guavapicker.ResponseHandler

object ContinueAddRoleButtonInteraction : IInteraction<ButtonInteractionCreateEvent> {
    override val handler: ResponseHandler<ButtonInteractionCreateEvent> = {
        val guildId = interaction.getChannel().data.guildId.value
            ?: throw GuavaPickerException("Couldn't find guild id")
        val row = interaction.message.actionRows.firstOrNull { it.selectMenus.isNotEmpty() }
            ?: throw GuavaPickerException("Couldn't find action row")
        val roleId = row.selectMenus.values.first().data.value.value
            ?: throw GuavaPickerException("No role was selected")
        val guild = kord.getGuild(guildId)
            ?: throw GuavaPickerException("Couldn't find guild")
        val role = guild.getRoleOrNull(Snowflake(roleId))
            ?: throw GuavaPickerException("Couldn't find role")
        interaction.modal("Customize button", "sdf") {
            actionRow {
                textInput(TextInputStyle.Short, "sda", "Emoji (optional)") {
                    required = false
                    allowedLength = IntRange(0, 1)
                }
                textInput(TextInputStyle.Short, "sdb", "Label (optional)") {
                    required = false
                    placeholder = role.name
                }
            }
        }
    }
}