package net.javaman.guavapicker.old.events

import dev.kord.core.event.interaction.SelectMenuInteractionCreateEvent
import net.javaman.guavapicker.ResponseHandler

object SelectMenuEvent : IEvent<SelectMenuInteractionCreateEvent> {
    override val handlerImpl: ResponseHandler<SelectMenuInteractionCreateEvent> = {
        when (interaction.componentId)
    }
}