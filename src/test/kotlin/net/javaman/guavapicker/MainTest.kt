package net.javaman.guavapicker

import dev.kord.common.entity.Snowflake
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent
import dev.kord.core.on
import kotlinx.coroutines.runBlocking
import net.javaman.guavapicker.events.ChatInputCommandEvent
import net.javaman.guavapicker.interactions.CreateInteraction
import org.junit.jupiter.api.Test

class MainTest {
    companion object {
        private val guild = Snowflake(972987144735952916)
    }

    @Test
    fun main() = runBlocking {
        val kord = startDiscord()
        kord.createGuildChatInputCommand(
            guild,
            CreateInteraction.name,
            CreateInteraction.description,
            CreateInteraction.builder
        )
        kord.on<GuildChatInputCommandInteractionCreateEvent>(kord, ChatInputCommandEvent.handle)
        logIntoDiscord(kord)
    }
}