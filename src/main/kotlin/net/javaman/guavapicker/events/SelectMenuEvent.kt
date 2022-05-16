package net.javaman.guavapicker.events

import dev.kord.core.event.interaction.SelectMenuInteractionCreateEvent
import net.javaman.guavapicker.ResponseHandler

object SelectMenuEvent : AEvent<SelectMenuInteractionCreateEvent>() {
    override val handlerImpl: ResponseHandler<SelectMenuInteractionCreateEvent> = {
        when (interaction.componentId)
    }
}