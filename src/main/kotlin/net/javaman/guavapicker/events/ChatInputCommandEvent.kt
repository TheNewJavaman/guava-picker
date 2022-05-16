package net.javaman.guavapicker.events

import dev.kord.core.event.interaction.ChatInputCommandInteractionCreateEvent
import net.javaman.guavapicker.GuavaPickerException
import net.javaman.guavapicker.ResponseHandler
import net.javaman.guavapicker.interactions.CreateCommandInteraction

object ChatInputCommandEvent : AEvent<ChatInputCommandInteractionCreateEvent>() {
    override val handlerImpl: ResponseHandler<ChatInputCommandInteractionCreateEvent> = { response ->
        when (interaction.invokedCommandName) {
            CreateCommandInteraction.name -> CreateCommandInteraction.handler(this, response)
            else -> throw GuavaPickerException("Command not found")
        }
    }
}