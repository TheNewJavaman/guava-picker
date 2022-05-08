package net.javaman.guavapicker

import dev.kord.common.entity.Snowflake
import dev.kord.core.Kord
import dev.kord.core.behavior.interaction.response.respond
import dev.kord.core.entity.application.ApplicationCommand
import dev.kord.core.entity.application.GuildChatInputCommand
import dev.kord.core.entity.application.GuildMessageCommand
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent
import dev.kord.core.event.interaction.GuildMessageCommandInteractionCreateEvent
import dev.kord.core.event.interaction.InteractionCreateEvent
import dev.kord.rest.builder.interaction.string
import kotlinx.datetime.Clock

abstract class ACommand<_Command : ApplicationCommand, _Event : InteractionCreateEvent> {
    protected lateinit var command: _Command

    abstract val register: suspend Kord.() -> Unit

    abstract val handle: suspend _Event.() -> Boolean
}

object CreateCommand : ACommand<GuildChatInputCommand, GuildChatInputCommandInteractionCreateEvent>() {
    override val register: suspend Kord.() -> Unit = {
        command = createGuildChatInputCommand(Snowflake(), "create", "Create a role picker in this channel") {
            string("message", "A message at the top of the picker") {
                required = false
            }
        }
    }

    override val handle: suspend GuildChatInputCommandInteractionCreateEvent.() -> Boolean = listen@{
        if (interaction.invokedCommandId != command.id) return@listen false
        val response = interaction.deferEphemeralResponse()
        response.respond {
            this.content =
                "Success \uD83D\uDC4D Right-click the role picker message, `Apps > Add Roles` to " + "get started"
        }
        true
    }
}

object AddRoleCommand : ACommand<GuildMessageCommand, GuildMessageCommandInteractionCreateEvent>() {
    override val register: suspend Kord.() -> Unit = {
        createGuildMessageCommand(Snowflake(), "Add Role")
    }

    override val handle: suspend GuildMessageCommandInteractionCreateEvent.() -> Boolean = listen@{
        if (interaction.invokedCommandId != command.id) return@listen false
        val response = interaction.deferEphemeralResponse()
        response.respond {
            this.content =
                "Success \uD83D\uDC4D Right-click the role picker message, `Apps > Add Roles` to " + "get started"
        }
        true
    }
}

private fun Snowflake() = Snowflake(Clock.System.now())
