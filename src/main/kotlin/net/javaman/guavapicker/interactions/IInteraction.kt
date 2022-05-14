package net.javaman.guavapicker.interactions

import dev.kord.core.event.interaction.ActionInteractionCreateEvent
import net.javaman.guavapicker.ResponseHandler

interface IInteraction<T : ActionInteractionCreateEvent> {
    val handler: ResponseHandler<T>
}
