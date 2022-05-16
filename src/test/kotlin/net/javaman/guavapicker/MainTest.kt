package net.javaman.guavapicker

import dev.kord.common.entity.Snowflake
import dev.kord.core.event.interaction.GuildButtonInteractionCreateEvent
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent
import dev.kord.core.event.interaction.GuildMessageCommandInteractionCreateEvent
import dev.kord.core.on
import kotlinx.coroutines.runBlocking
import net.javaman.guavapicker.events.ButtonEvent
import net.javaman.guavapicker.events.ChatInputCommandEvent
import net.javaman.guavapicker.events.MessageCommandEvent
import net.javaman.guavapicker.interactions.AddRoleSelectMenuInteraction
import net.javaman.guavapicker.interactions.CreateCommandInteraction
import org.junit.jupiter.api.Test

class MainTest {
    companion object {
        private val guild = Snowflake(972987144735952916)
    }

    @Test
    fun main() = runBlocking {
        startDiscord {
            createGuildChatInputCommand(
                guild,
                CreateCommandInteraction.name,
                CreateCommandInteraction.description,
                CreateCommandInteraction.builder
            )
            createGuildMessageCommand(guild, AddRoleSelectMenuInteraction.name, AddRoleSelectMenuInteraction.builder)
            on<GuildChatInputCommandInteractionCreateEvent>(this, ChatInputCommandEvent.handler)
            on<GuildButtonInteractionCreateEvent>(this, ButtonEvent.handler)
            on<GuildMessageCommandInteractionCreateEvent>(this, MessageCommandEvent.handler)
        }
    }
}