package net.javaman.guavapicker.discord.handlers

import dev.kord.core.event.interaction.ModalSubmitInteractionCreateEvent
import net.javaman.guavapicker.GuavaPickerException
import net.javaman.guavapicker.discord.Handler
import net.javaman.guavapicker.discord.handlingExceptions
import net.javaman.guavapicker.discord.interactions.CreateDescriptionInteraction

val modalSubmitHandler: Handler<ModalSubmitInteractionCreateEvent> = {
    handlingExceptions {
        when (interaction.modalId.split("-").first()) {
            CreateDescriptionInteraction.ID -> CreateDescriptionInteraction.handler(this)
            else -> throw GuavaPickerException("Modal id not recognized")
        }
    }
}
