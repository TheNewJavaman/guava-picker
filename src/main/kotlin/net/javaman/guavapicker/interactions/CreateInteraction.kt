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
import kotlinx.serialization.json.Json
import mu.KotlinLogging
import net.javaman.guavapicker.embedTemplate
import net.javaman.guavapicker.models.RoleButtonCustomId

object CreateInteraction {
    const val name = "create"
    const val description = "Create a role picker with one button"

    private val logger = KotlinLogging.logger {}

    val builder: ChatInputCreateBuilder.() -> Unit = {
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

    val handle: suspend ChatInputCommandInteractionCreateEvent.() -> Unit = event@{
        val response = interaction.deferEphemeralResponse()
        val role = interaction.command.roles["role"] ?: run {
            response.respond {
                embed(embedTemplate("Something went wrong", "Couldn't find that role"))
            }
            logger.warn { "Couldn't find role in message" }
            return@event
        }
        val label = interaction.command.strings["label"] ?: role.name
        val header = interaction.command.strings["header"]
        val message = interaction.command.strings["message"]
        val model = RoleButtonCustomId(role.id, label)
        try {
            interaction.channel.createMessage {
                if (header != null || message != null) {
                    embed(embedTemplate(header, message))
                }
                actionRow {
                    interactionButton(ButtonStyle.Secondary, Json.encodeToString(model)) {
                        this.label = label
                        interaction.command.strings["emoji"]?.let {
                            emoji(ReactionEmoji.Unicode(it))
                        }
                    }
                }
            }
            response.respond {
                embed(
                    embedTemplate(
                        "Role picker created",
                        "Right-click the message, then click `Apps > Add Role` to add additional buttons"
                    )
                )
            }
        } catch (e: Exception) {
            response.respond {
                embed(embedTemplate("Something went wrong", "Double-check your emoji"))
            }
            logger.warn { "Creating the message failed; it's probably an issue with the user's emoji" }
        }
    }
}