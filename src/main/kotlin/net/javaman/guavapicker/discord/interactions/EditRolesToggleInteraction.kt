package net.javaman.guavapicker.discord.interactions

import dev.kord.common.entity.Snowflake
import dev.kord.core.behavior.interaction.response.edit
import dev.kord.core.event.interaction.ButtonInteractionCreateEvent
import net.javaman.guavapicker.discord.buildEditor
import net.javaman.guavapicker.sql.editors.Editor
import net.javaman.guavapicker.sql.pickerroles.PickerRole
import org.jetbrains.exposed.sql.transactions.transaction

object EditRolesToggleInteraction {
    const val INDICATOR_PREFIX = "editRolesIndicator-"
    const val LABEL_PREFIX = "editRolesButton-"

    val handle: suspend ButtonInteractionCreateEvent.() -> Unit = {
        val response = interaction.deferEphemeralMessageUpdate()
        val roleIdString = interaction.componentId
            .replaceFirst(INDICATOR_PREFIX, "")
            .replaceFirst(LABEL_PREFIX, "")
        val roleId = Snowflake(roleIdString.toULong())
        val originalMessageId = interaction.getOriginalInteractionResponse().asMessage().id
        val picker = transaction {
            val picker = Editor.findByMessageId(originalMessageId).first().picker
            PickerRole.findByRoleId(roleId).firstOrNull()?.delete() ?: run {
                PickerRole.new {
                    this.picker = picker
                    this.roleId = roleId
                }
            }
            picker
        }
        val pickerRoles = transaction {
            PickerRole.findByPicker(picker).toList()
        }
        response.edit(buildEditor(pickerRoles))
    }
}