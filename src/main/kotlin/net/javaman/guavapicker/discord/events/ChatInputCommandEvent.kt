package net.javaman.guavapicker.discord.events

import dev.kord.core.event.interaction.ChatInputCommandInteractionCreateEvent
import net.javaman.guavapicker.discord.Handler
import net.javaman.guavapicker.discord.handlingExceptions
import net.javaman.guavapicker.discord.interactions.CreateInteraction

object ChatInputCommandEvent {
    val handler: Handler<ChatInputCommandInteractionCreateEvent> = {
        handlingExceptions {
            when (interaction.invokedCommandName) {
                CreateInteraction.name -> CreateInteraction.handler(this)
                else -> throw Exception("Chat input command not recognized")
            }
        }
    }
}

