package net.javaman.guavapicker.events

import dev.kord.core.event.interaction.ButtonInteractionCreateEvent
import kotlinx.serialization.decodeFromString
import net.javaman.guavapicker.GuavaPickerException
import net.javaman.guavapicker.ResponseHandler
import net.javaman.guavapicker.interactions.ContinueAddRoleButtonInteraction
import net.javaman.guavapicker.interactions.ToggleRoleButtonInteraction
import net.javaman.guavapicker.models.CustomId
import net.javaman.guavapicker.models.OCustomIdModel
import net.javaman.guavapicker.serializer

object ButtonEvent : AEvent<ButtonInteractionCreateEvent>() {
    override val handlerImpl: ResponseHandler<ButtonInteractionCreateEvent> = { response ->
        when (serializer.decodeFromString<OCustomIdModel>(interaction.componentId).id) {
            CustomId.TOGGLE_ROLE_BUTTON -> ToggleRoleButtonInteraction.handler(this, response)
            CustomId.CONTINUE_ADD_ROLE_BUTTON -> ContinueAddRoleButtonInteraction.handler(this, response)
            else -> throw GuavaPickerException("Id not found")
        }
    }
}