package net.javaman.guavapicker.discord.interactions

import dev.kord.core.behavior.interaction.modal
import dev.kord.core.event.interaction.ChatInputCommandInteractionCreateEvent
import dev.kord.rest.builder.interaction.ChatInputCreateBuilder
import net.javaman.guavapicker.discord.Handler
import net.javaman.guavapicker.discord.templates.DescriptionModalTemplate

object CreateInteraction {
    const val NAME = "create"
    const val DESCRIPTION = "Create a role toggle button"

    val builder: ChatInputCreateBuilder.() -> Unit = {}

    val handler: Handler<ChatInputCommandInteractionCreateEvent> = {
        interaction.modal("Write a description", CreateDescriptionInteraction.ID, DescriptionModalTemplate())
    }
}