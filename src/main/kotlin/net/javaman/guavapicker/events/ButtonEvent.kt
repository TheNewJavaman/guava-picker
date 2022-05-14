package net.javaman.guavapicker.events

import dev.kord.core.event.interaction.ButtonInteractionCreateEvent
import kotlinx.serialization.decodeFromString
import net.javaman.guavapicker.GuavaPickerException
import net.javaman.guavapicker.ResponseHandler
import net.javaman.guavapicker.interactions.RoleButtonInteraction
import net.javaman.guavapicker.models.CustomId
import net.javaman.guavapicker.models.OCustomIdModel
import net.javaman.guavapicker.serializer

object ButtonEvent : AEvent<ButtonInteractionCreateEvent>() {
    override val handlerImpl: ResponseHandler<ButtonInteractionCreateEvent> = { response ->
        when (serializer.decodeFromString<OCustomIdModel>(interaction.componentId).id) {
            CustomId.ROLE_BUTTON -> RoleButtonInteraction.handler(this, response)
            else -> throw GuavaPickerException("Id not found")
        }
    }
}