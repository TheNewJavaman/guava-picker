package net.javaman.guavapicker.interactions

import dev.kord.core.behavior.interaction.response.respond
import dev.kord.core.event.interaction.MessageCommandInteractionCreateEvent
import dev.kord.rest.builder.interaction.MessageCommandCreateBuilder
import dev.kord.rest.builder.message.modify.actionRow
import dev.kord.rest.builder.message.modify.embed
import kotlinx.coroutines.flow.toList
import net.javaman.guavapicker.ResponseHandler
import net.javaman.guavapicker.templates.EmbedTemplate

object AddRoleSelectMenuInteraction : IInteraction<MessageCommandInteractionCreateEvent> {
    const val name = "Add role"

    val builder: MessageCommandCreateBuilder.() -> Unit = {
        defaultPermission = false
    }

    override val handler: ResponseHandler<MessageCommandInteractionCreateEvent> = { response ->
        val roles = interaction.getTarget().getGuild().roles.toList()
        response.respond {
            embed(EmbedTemplate("Add role picker"))
            actionRow {
                selectMenu("roleSelectMenu") {
                    placeholder = "Select a role"
                    for (role in roles) {
                        option(role.name, role.id.value.toString())
                    }
                }
            }
        }
    }
}