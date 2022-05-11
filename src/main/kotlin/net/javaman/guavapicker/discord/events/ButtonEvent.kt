package net.javaman.guavapicker.discord.events

import dev.kord.core.event.interaction.ButtonInteractionCreateEvent
import net.javaman.guavapicker.discord.interactions.EditRolesToggleInteraction

object ButtonEvent {
    val handle: suspend ButtonInteractionCreateEvent.() -> Unit = {
        when {
            interaction.componentId.startsWith(EditRolesToggleInteraction.INDICATOR_PREFIX)
                    || interaction.componentId.startsWith(EditRolesToggleInteraction.LABEL_PREFIX) ->
                EditRolesToggleInteraction.handle(this)
        }
    }
}