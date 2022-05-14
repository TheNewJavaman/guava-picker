package net.javaman.guavapicker.events

import dev.kord.core.event.interaction.ChatInputCommandInteractionCreateEvent
import net.javaman.guavapicker.interactions.CreateInteraction

object ChatInputCommandEvent {
    val handle: suspend ChatInputCommandInteractionCreateEvent.() -> Unit = {
        when (interaction.invokedCommandName) {
            CreateInteraction.name -> CreateInteraction.handle(this)
        }
    }
}