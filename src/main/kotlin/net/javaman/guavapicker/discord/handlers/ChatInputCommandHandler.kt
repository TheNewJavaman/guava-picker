package net.javaman.guavapicker.discord.handlers

import dev.kord.core.event.interaction.ChatInputCommandInteractionCreateEvent
import net.javaman.guavapicker.GuavaPickerException
import net.javaman.guavapicker.discord.Handler
import net.javaman.guavapicker.discord.handlingExceptions
import net.javaman.guavapicker.discord.interactions.CreateInteraction

val chatInputCommandHandler: Handler<ChatInputCommandInteractionCreateEvent> = {
    handlingExceptions {
        when (interaction.invokedCommandName) {
            CreateInteraction.NAME -> CreateInteraction.handler(this)
            else -> throw GuavaPickerException("Chat input command not recognized")
        }
    }
}
