package net.javaman.guavapicker.events

import dev.kord.core.event.interaction.MessageCommandInteractionCreateEvent
import net.javaman.guavapicker.GuavaPickerException
import net.javaman.guavapicker.ResponseHandler
import net.javaman.guavapicker.interactions.AddRoleSelectMenuInteraction

object MessageCommandEvent : AEvent<MessageCommandInteractionCreateEvent>() {
    override val handlerImpl: ResponseHandler<MessageCommandInteractionCreateEvent> = { response ->
        when (interaction.invokedCommandName) {
            AddRoleSelectMenuInteraction.name -> AddRoleSelectMenuInteraction.handler(this, response)
            else -> throw GuavaPickerException("Command not found")
        }
    }
}