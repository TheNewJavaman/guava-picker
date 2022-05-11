package net.javaman.guavapicker.discord.interactions

import dev.kord.core.behavior.channel.createMessage
import dev.kord.core.event.interaction.ChatInputCommandInteractionCreateEvent
import dev.kord.rest.builder.interaction.ChatInputCreateBuilder
import dev.kord.rest.builder.interaction.string
import dev.kord.rest.builder.message.create.embed
import net.javaman.guavapicker.discord.embedColor
import net.javaman.guavapicker.discord.respondEmbed
import net.javaman.guavapicker.sql.pickers.Picker
import org.jetbrains.exposed.sql.transactions.transaction

object CreateInteraction {
    const val name = "create"
    const val description = "Create a role picker"

    val builder: ChatInputCreateBuilder.() -> Unit = {
        string("message", "A message at the top of the picker") {
            required = false
        }
    }

    val handle: suspend ChatInputCommandInteractionCreateEvent.() -> Unit = {
        val response = interaction.deferEphemeralResponse()
        val message = interaction.command.strings["message"] ?: "**Select roles below**"
        val messageId = interaction.channel.createMessage {
            embed {
                color = embedColor
                description = message
            }
        }.id
        response.respondEmbed(
            "Success",
            "Role picker created! Right-click the message, then click `Apps > Add Roles` to get started"
        )
        transaction {
            Picker.new {
                this.messageId = messageId
                this.message = message
            }
        }
    }
}