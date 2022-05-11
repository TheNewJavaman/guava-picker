package net.javaman.guavapicker.discord.events

import dev.kord.core.event.interaction.MessageCommandInteractionCreateEvent
import net.javaman.guavapicker.discord.interactions.EditRolesInteraction

object MessageCommandEvent {
    val handle: suspend MessageCommandInteractionCreateEvent.() -> Unit = {
        when (interaction.invokedCommandName) {
            EditRolesInteraction.name -> EditRolesInteraction.handle(this)
        }
    }
}