package net.javaman.guavapicker.old.events

import dev.kord.core.event.interaction.ChatInputCommandInteractionCreateEvent
import net.javaman.guavapicker.GuavaPickerException
import net.javaman.guavapicker.ResponseHandler
import net.javaman.guavapicker.old.interactions.CreateCommandInteraction

object ChatInputCommandEvent : IEvent<ChatInputCommandInteractionCreateEvent>() {
    val handler: ResponseHandler<ChatInputCommandInteractionCreateEvent> = { response ->
        when (interaction.invokedCommandName) {
            CreateCommandInteraction.name -> CreateCommandInteraction.handler(this, response)
            else -> throw GuavaPickerException("Command not found")
        }
    }
}