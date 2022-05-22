package net.javaman.guavapicker.discord.interactions.roletogglebutton

import dev.kord.common.entity.Snowflake
import dev.kord.core.behavior.channel.createMessage
import dev.kord.core.behavior.interaction.respondEphemeral
import dev.kord.core.event.interaction.ModalSubmitInteractionCreateEvent
import kotlinx.coroutines.flow.toList
import net.javaman.guavapicker.GuavaPickerException
import net.javaman.guavapicker.discord.Handler
import net.javaman.guavapicker.discord.templates.roletogglebutton.rtbMessageTemplate
import net.javaman.guavapicker.discord.templates.roletogglebutton.rtbButtonSetupMessageTemplate
import net.javaman.guavapicker.dynamodb.items.RtbMessageItem
import net.javaman.guavapicker.dynamodb.roletogglebutton.RtbMessagesTable

object RtbCreateModalInteraction {
    const val ID = "createDescription"
    const val HEADER_ID = "header"
    const val BODY_ID = "body"

    val handler: Handler<ModalSubmitInteractionCreateEvent> = {
        val guildId = interaction.data.guildId.value ?: throw GuavaPickerException("Guild id not found")
        val guild = kord.getGuild(guildId) ?: throw GuavaPickerException("Guild not found")
        val roles = guild.roles.toList()

        val header = interaction.textInputs["header"]?.value?.let { if (it == "") null else it }
        val body = interaction.textInputs["body"]?.value?.let { if (it == "") null else it }

        if (header != null || body != null) {
            val model = RtbMessageItem(Snowflake.min, listOf(), header, body)
            val message = interaction.getChannel().createMessage(rtbMessageTemplate(model))
            RtbMessagesTable.put(model.copy(messageId = message.id))
            interaction.respondEphemeral(rtbButtonSetupMessageTemplate(message.id.value, roles))
        } else {
            interaction.respondEphemeral(rtbButtonSetupMessageTemplate(RtbAddRoleMessageInteraction.NO_MESSAGE_ID, roles))
        }
    }
}