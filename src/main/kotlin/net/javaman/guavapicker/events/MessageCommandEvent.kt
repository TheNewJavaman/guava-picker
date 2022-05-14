package net.javaman.guavapicker.events

import dev.kord.core.event.interaction.MessageCommandInteractionCreateEvent
import net.javaman.guavapicker.GuavaPickerException
import net.javaman.guavapicker.ResponseHandler

object MessageCommandEvent : AEvent<MessageCommandInteractionCreateEvent>() {
    override val handlerImpl: ResponseHandler<MessageCommandInteractionCreateEvent> = { response ->
        when (interaction.invokedCommandName) {
            else -> throw GuavaPickerException("Command not found")
        }
    }
}