package net.javaman.guavapicker.discord.interactions

import dev.kord.core.behavior.channel.createMessage
import dev.kord.core.behavior.interaction.respondEphemeral
import dev.kord.core.event.interaction.ModalSubmitInteractionCreateEvent
import kotlinx.coroutines.flow.toList
import net.javaman.guavapicker.GuavaPickerException
import net.javaman.guavapicker.discord.Handler
import net.javaman.guavapicker.discord.templates.roleToggleMessageTemplate
import net.javaman.guavapicker.discord.templates.roleToggleSelectTemplate
import net.javaman.guavapicker.dynamodb.items.RoleToggleMessage
import net.javaman.guavapicker.dynamodb.tables.RoleToggleMessagesTable

object CreateDescriptionInteraction {
    const val ID = "createDescription"
    const val HEADER_ID = "header"
    const val BODY_ID = "body"

    val handler: Handler<ModalSubmitInteractionCreateEvent> = {
        val header = interaction.textInputs["header"]?.value?.let { if (it == "") null else it }
        val body = interaction.textInputs["body"]?.value?.let { if (it == "") null else it }
        val guildId = interaction.data.guildId.value ?: throw GuavaPickerException("Guild id not found")
        val guild = kord.getGuild(guildId) ?: throw GuavaPickerException("Guild not found")
        val roles = guild.roles.toList()
        if (header != null || body != null) {
            val model = RoleToggleMessage(null, listOf(), header, body)
            val message = interaction.getChannel().createMessage(roleToggleMessageTemplate(model))
            RoleToggleMessagesTable.put(model.copy(messageId = message.id))
            interaction.respondEphemeral(roleToggleSelectTemplate(message.id.value, roles))
        } else {
            interaction.respondEphemeral(roleToggleSelectTemplate(AddRoleSelectInteraction.NO_MESSAGE_ID, roles))
        }
    }
}