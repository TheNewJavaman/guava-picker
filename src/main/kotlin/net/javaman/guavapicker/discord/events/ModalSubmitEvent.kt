package net.javaman.guavapicker.discord.events

import dev.kord.core.event.interaction.ModalSubmitInteractionCreateEvent
import net.javaman.guavapicker.GuavaPickerException
import net.javaman.guavapicker.discord.Handler
import net.javaman.guavapicker.discord.interactions.CreateDescriptionInteraction

class ModalSubmitEvent : Event<ModalSubmitInteractionCreateEvent>() {
    override val handlerImpl: Handler<ModalSubmitInteractionCreateEvent> = {
        when (interaction.modalId.split("-").first()) {
            CreateDescriptionInteraction.ID -> CreateDescriptionInteraction.handler(this)
            else -> throw GuavaPickerException("Modal id not recognized")
        }
    }
}