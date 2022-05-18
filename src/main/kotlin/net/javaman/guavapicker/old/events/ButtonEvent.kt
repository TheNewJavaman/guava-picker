package net.javaman.guavapicker.old.events

import dev.kord.core.event.interaction.ButtonInteractionCreateEvent
import kotlinx.serialization.decodeFromString
import net.javaman.guavapicker.GuavaPickerException
import net.javaman.guavapicker.Handler
import net.javaman.guavapicker.ResponseHandler
import net.javaman.guavapicker.catchingResponse
import net.javaman.guavapicker.old.interactions.ContinueAddRoleButtonInteraction
import net.javaman.guavapicker.old.interactions.ToggleRoleButtonInteraction
import net.javaman.guavapicker.old.models.CustomId
import net.javaman.guavapicker.old.models.OCustomIdModel
import net.javaman.guavapicker.serializer

object ButtonEvent : IEvent<ButtonInteractionCreateEvent> {
    val handlerImpl: ResponseHandler<ButtonInteractionCreateEvent> = { response ->

    }

    override val handler: Handler<ButtonInteractionCreateEvent> = {
        catchingResponse {
            when (serializer.decodeFromString<OCustomIdModel>(interaction.componentId).id) {
                CustomId.TOGGLE_ROLE_BUTTON -> ToggleRoleButtonInteraction.handler(this, response)
                CustomId.CONTINUE_ADD_ROLE_BUTTON -> ContinueAddRoleButtonInteraction.handler(this, response)
                else -> throw GuavaPickerException("Id not found")
            }
        }
        val model = try {
            serializer.decodeFromString<OCustomIdModel>(interaction.componentId)
        } catch (e: Exception) {
            throw Exception("Couldn't ")
        }
    }
}