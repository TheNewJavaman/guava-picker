package net.javaman.guavapicker.interactions

import dev.kord.core.event.interaction.MessageCommandInteractionCreateEvent
import dev.kord.rest.builder.interaction.MessageCommandCreateBuilder
import net.javaman.guavapicker.ResponseHandler

object AddRoleInteraction : IInteraction<MessageCommandInteractionCreateEvent> {
    const val name = "Add role"

    val builder: MessageCommandCreateBuilder.() -> Unit = {
        defaultPermission = false
    }

    override val handler: ResponseHandler<MessageCommandInteractionCreateEvent> = {

    }
}