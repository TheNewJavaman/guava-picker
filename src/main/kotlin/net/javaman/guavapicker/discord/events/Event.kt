package net.javaman.guavapicker.discord.events

import dev.kord.core.event.interaction.ActionInteractionCreateEvent
import net.javaman.guavapicker.discord.Handler
import net.javaman.guavapicker.discord.handlingExceptions

abstract class Event<T : ActionInteractionCreateEvent> {
    val handler: Handler<T> = {
        handlingExceptions {
            handlerImpl()
        }
    }

    protected abstract val handlerImpl: Handler<T>
}