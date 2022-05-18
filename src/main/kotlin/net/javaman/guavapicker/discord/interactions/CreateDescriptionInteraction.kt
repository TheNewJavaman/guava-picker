package net.javaman.guavapicker.discord.interactions

import dev.kord.core.event.interaction.ModalSubmitInteractionCreateEvent
import net.javaman.guavapicker.discord.Handler
import net.javaman.guavapicker.dynamodb.ddbClient

object CreateDescriptionInteraction {
    const val ID_PREFIX = "createDescription-"
    const val HEADER_ID = "header"
    const val BODY_ID = "body"

    val handler: Handler<ModalSubmitInteractionCreateEvent> = {
        ddbClient
    }
}