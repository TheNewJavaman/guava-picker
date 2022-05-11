package net.javaman.guavapicker.discord.events

import dev.kord.core.event.interaction.ChatInputCommandInteractionCreateEvent
import net.javaman.guavapicker.discord.interactions.CreateInteraction

object ChatInputCommandEvent {
    val handle: suspend ChatInputCommandInteractionCreateEvent.() -> Unit = {
        when (interaction.invokedCommandName) {
            CreateInteraction.name -> CreateInteraction.handle(this)
        }
    }
}