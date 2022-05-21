package net.javaman.guavapicker.discord.events

import dev.kord.core.event.interaction.ChatInputCommandInteractionCreateEvent
import net.javaman.guavapicker.GuavaPickerException
import net.javaman.guavapicker.discord.Handler
import net.javaman.guavapicker.discord.interactions.CreateInteraction

object ChatInputCommandEvent : Event<ChatInputCommandInteractionCreateEvent>() {
    override val handlerImpl: Handler<ChatInputCommandInteractionCreateEvent> = {
        when (interaction.invokedCommandName) {
            CreateInteraction.NAME -> CreateInteraction.handler(this)
            else -> throw GuavaPickerException("Chat input command not recognized")
        }
    }
}
