package net.javaman.guavapicker

import dev.kord.common.entity.Snowflake
import dev.kord.core.event.interaction.GlobalButtonInteractionCreateEvent
import dev.kord.core.event.interaction.GuildButtonInteractionCreateEvent
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent
import dev.kord.core.event.interaction.GuildMessageCommandInteractionCreateEvent
import dev.kord.core.on
import kotlinx.coroutines.runBlocking
import net.javaman.guavapicker.discord.events.ButtonEvent
import net.javaman.guavapicker.discord.events.ChatInputCommandEvent
import net.javaman.guavapicker.discord.events.MessageCommandEvent
import net.javaman.guavapicker.discord.interactions.CreateInteraction
import net.javaman.guavapicker.discord.interactions.EditRolesInteraction
import net.javaman.guavapicker.discord.logIntoDiscord
import net.javaman.guavapicker.discord.startDiscord
import net.javaman.guavapicker.sql.startDatabase
import org.junit.jupiter.api.Test

class MainTest {
    companion object {
        private val testGuildId = Snowflake(972987144735952916)
    }

    @Test
    fun main() = runBlocking {
        startDatabase()

        val kord = startDiscord()
        kord.createGuildChatInputCommand(
            testGuildId,
            CreateInteraction.name,
            CreateInteraction.description,
            CreateInteraction.builder
        )
        kord.createGuildMessageCommand(testGuildId, EditRolesInteraction.name, EditRolesInteraction.builder)
        kord.on<GuildChatInputCommandInteractionCreateEvent>(kord, ChatInputCommandEvent.handle)
        kord.on<GuildMessageCommandInteractionCreateEvent>(kord, MessageCommandEvent.handle)
        kord.on<GuildButtonInteractionCreateEvent>(kord, ButtonEvent.handle)
        logIntoDiscord(kord)
    }
}