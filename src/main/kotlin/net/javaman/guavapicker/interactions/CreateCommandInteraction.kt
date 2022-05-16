package net.javaman.guavapicker.interactions

import dev.kord.common.entity.ButtonStyle
import dev.kord.core.behavior.channel.createMessage
import dev.kord.core.behavior.interaction.response.respond
import dev.kord.core.builder.components.emoji
import dev.kord.core.entity.ReactionEmoji
import dev.kord.core.event.interaction.ChatInputCommandInteractionCreateEvent
import dev.kord.rest.builder.interaction.ChatInputCreateBuilder
import dev.kord.rest.builder.interaction.role
import dev.kord.rest.builder.interaction.string
import dev.kord.rest.builder.message.create.actionRow
import dev.kord.rest.builder.message.create.embed
import dev.kord.rest.builder.message.modify.embed
import kotlinx.serialization.encodeToString
import net.javaman.guavapicker.GuavaPickerException
import net.javaman.guavapicker.ResponseHandler
import net.javaman.guavapicker.models.ToggleRoleButtonModel
import net.javaman.guavapicker.serializer
import net.javaman.guavapicker.templates.EmbedTemplate

object CreateCommandInteraction : IInteraction<ChatInputCommandInteractionCreateEvent> {
    const val name = "create"
    const val description = "Create a role picker with one button"

    val builder: ChatInputCreateBuilder.() -> Unit = {
        defaultPermission = false
        role("role", "The role to toggle") {
            required = true
        }
        string("header", "A header at the top; defaults to no header") {
            required = false
        }
        string("message", "A description above the button; defaults to no message") {
            required = false
        }
        string("label", "A label on the button; defaults to the role name") {
            required = false
        }
        string("emoji", "A unicode emoji on the button; defaults to no emoji") {
            required = false
        }
    }

    override val handler: ResponseHandler<ChatInputCommandInteractionCreateEvent> = { response ->
        val role = interaction.command.roles["role"]
            ?: throw GuavaPickerException("Couldn't find role in command parameters")
        val label = interaction.command.strings["label"] ?: role.name
        val header = interaction.command.strings["header"]
        val message = interaction.command.strings["message"]
        interaction.channel.createMessage {
            if (header != null || message != null) {
                embed(EmbedTemplate(header, message))
            }
            actionRow {
                interactionButton(ButtonStyle.Secondary, serializer.encodeToString(ToggleRoleButtonModel(role.id))) {
                    this.label = label
                    interaction.command.strings["emoji"]?.let {
                        emoji(ReactionEmoji.Unicode(it))
                    }
                }
            }
        }
        response.respond {
            embed(
                EmbedTemplate(
                    "Role picker created",
                    "Right-click the message, then click `Apps > Add Role` to add additional buttons"
                )
            )
        }
    }
}