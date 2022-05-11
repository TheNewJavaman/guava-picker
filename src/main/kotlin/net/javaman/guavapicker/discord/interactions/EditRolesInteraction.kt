package net.javaman.guavapicker.discord.interactions

import dev.kord.core.behavior.interaction.response.respond
import dev.kord.core.event.interaction.MessageCommandInteractionCreateEvent
import dev.kord.rest.builder.interaction.MessageCommandCreateBuilder
import mu.KotlinLogging
import net.javaman.guavapicker.discord.buildEditor
import net.javaman.guavapicker.discord.checkUser
import net.javaman.guavapicker.discord.respondEmbed
import net.javaman.guavapicker.sql.editors.Editor
import net.javaman.guavapicker.sql.pickerroles.PickerRole
import net.javaman.guavapicker.sql.pickers.Picker
import org.jetbrains.exposed.sql.transactions.transaction

private val logger = KotlinLogging.logger {}

object EditRolesInteraction {
    const val name = "Edit Roles"

    val builder: MessageCommandCreateBuilder.() -> Unit = {}

    val handle: suspend MessageCommandInteractionCreateEvent.() -> Unit = event@{
        val response = interaction.deferEphemeralResponse()
        if (!checkUser(response)) return@event
        val picker = transaction {
            Picker.findByMessageId(interaction.targetId).firstOrNull()
        } ?: run {
            logger.warn {
                "Couldn't find any role picker data for Message(id=${interaction.targetId}); user probably " +
                        "clicked on the wrong message"
            }
            response.respondEmbed("Error", "Couldn't find any role picker data for this message!")
            return@event
        }
        val pickerRoles = transaction {
            PickerRole.findByPicker(picker).toList()
        }
        val messageId = response.respond(buildEditor(pickerRoles)).message.id
        transaction {
            Editor.new {
                this.messageId = messageId
                this.picker = picker
            }
        }
    }
}