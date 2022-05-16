package net.javaman.guavapicker.interactions

import dev.kord.core.behavior.interaction.response.respond
import dev.kord.core.event.interaction.ButtonInteractionCreateEvent
import dev.kord.rest.builder.message.modify.embed
import kotlinx.coroutines.flow.toList
import kotlinx.serialization.decodeFromString
import net.javaman.guavapicker.ResponseHandler
import net.javaman.guavapicker.models.ToggleRoleButtonModel
import net.javaman.guavapicker.serializer
import net.javaman.guavapicker.templates.EmbedTemplate

object ToggleRoleButtonInteraction : IInteraction<ButtonInteractionCreateEvent> {
    override val handler: ResponseHandler<ButtonInteractionCreateEvent> = { response ->
        val model = serializer.decodeFromString<ToggleRoleButtonModel>(interaction.componentId)
        val member = interaction.user.fetchMember(interaction.message.getGuild().id)
        if (model.role in member.roles.toList().map { it.id }) {
            member.removeRole(model.role)
            response.respond {
                embed(EmbedTemplate("Role removed"))
            }
        } else {
            member.addRole(model.role)
            response.respond {
                embed(EmbedTemplate("Role added"))
            }
        }
    }
}