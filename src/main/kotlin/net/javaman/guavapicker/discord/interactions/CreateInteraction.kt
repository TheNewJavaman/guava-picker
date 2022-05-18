package net.javaman.guavapicker.discord.interactions

import dev.kord.core.behavior.interaction.modal
import dev.kord.core.event.interaction.ChatInputCommandInteractionCreateEvent
import dev.kord.rest.builder.interaction.ChatInputCreateBuilder
import net.javaman.guavapicker.discord.Handler
import net.javaman.guavapicker.discord.templates.DescriptionTemplate

object CreateInteraction {
    const val name = "create"
    const val description = "Create a role picker with one button"

    val build: ChatInputCreateBuilder.() -> Unit = {
        defaultPermission = false
    }

    val handler: Handler<ChatInputCommandInteractionCreateEvent> = {
        interaction.modal("Write a description", "${CreateDescriptionInteraction.ID_PREFIX}-0", DescriptionTemplate())
    }
}