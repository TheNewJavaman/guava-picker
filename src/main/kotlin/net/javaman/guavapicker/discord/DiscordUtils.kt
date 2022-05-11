package net.javaman.guavapicker.discord

import dev.kord.common.Color
import dev.kord.common.entity.ButtonStyle
import dev.kord.common.entity.DiscordPartialEmoji
import dev.kord.core.Kord
import dev.kord.core.behavior.interaction.response.DeferredEphemeralMessageInteractionResponseBehavior
import dev.kord.core.behavior.interaction.response.respond
import dev.kord.core.event.interaction.InteractionCreateEvent
import dev.kord.gateway.Intent
import dev.kord.gateway.PrivilegedIntent
import dev.kord.rest.builder.message.modify.InteractionResponseModifyBuilder
import dev.kord.rest.builder.message.modify.actionRow
import dev.kord.rest.builder.message.modify.embed
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import mu.KotlinLogging
import net.javaman.guavapicker.discord.interactions.EditRolesRefreshInteraction
import net.javaman.guavapicker.discord.interactions.EditRolesToggleInteraction
import net.javaman.guavapicker.sql.pickerroles.PickerRole

private val logger = KotlinLogging.logger {}

val embedColor = Color(209, 96, 86)

suspend fun startDiscord(): Kord {
    logger.info { "Starting Discord client" }
    return Kord(System.getenv("GUAVA_DISCORD_TOKEN"))
}

suspend fun logIntoDiscord(kord: Kord) {
    logger.info { "Logging in Discord client" }
    kord.login {
        @OptIn(PrivilegedIntent::class)
        intents += Intent.MessageContent
    }
}

suspend fun InteractionCreateEvent.checkUser(response: DeferredEphemeralMessageInteractionResponseBehavior): Boolean {
    val guildId = interaction.data.guildId.value ?: run {
        logger.error { "guildId wasn't present in EditRoles MessageCommandInteractionCreateEvent" }
        response.respondEmbed("Error", "Couldn't find which server you're in!")
        return false
    }
    val guild = kord.getGuild(guildId) ?: run {
        logger.error { "Guild(id=$guildId) not found" }
        response.respondEmbed("Error", "Couldn't find which server you're in!")
        return false
    }
    val adminRole = guild.roles.firstOrNull { it.name == "guavapicker-admin" } ?: run {
        response.respondEmbed("Error", "You need the `guavapicker-admin` role to run this command!")
        return false
    }
    if (!(interaction.data.member.value?.roles ?: emptyList()).contains(adminRole.id)) {
        response.respondEmbed("Error", "You need the `guavapicker-admin` role to run this command!")
        return false
    }
    return true
}

suspend fun DeferredEphemeralMessageInteractionResponseBehavior.respondEmbed(header: String, message: String) {
    respond {
        embed {
            color = embedColor
            author {
                name = header
            }
            description = message
        }
    }
}

suspend fun InteractionCreateEvent.buildEditor(pickerRoles: List<PickerRole>): InteractionResponseModifyBuilder.() -> Unit {
    val pickerRoleIds = pickerRoles.map { it.roleId }
    val guild = kord.getGuild(interaction.data.guildId.value!!)
    val sortedRoles = guild!!.roles.toList().sortedBy { it.name }
    return {
        embed {
            color = embedColor
            author {
                name = "Select roles below"
            }
        }
        for (role in sortedRoles) {
            actionRow {
                val indicatorId = EditRolesToggleInteraction.INDICATOR_PREFIX + role.id.value.toString()
                if (pickerRoleIds.contains(role.id)) {
                    interactionButton(ButtonStyle.Primary, indicatorId) {
                        emoji = DiscordPartialEmoji(name = "\u2B1C")
                    }
                } else {
                    interactionButton(ButtonStyle.Secondary, indicatorId) {
                        emoji = DiscordPartialEmoji(name = "\uD83D\uDD33")
                    }
                }
                val buttonId = EditRolesToggleInteraction.LABEL_PREFIX + role.id.value.toString()
                interactionButton(ButtonStyle.Secondary, buttonId) {
                    label = role.name
                }
            }
        }
        actionRow {
            interactionButton(ButtonStyle.Secondary, EditRolesRefreshInteraction.BUTTON_ID) {
                emoji = DiscordPartialEmoji(name = "\uD83D\uDD04")
                label = "Refresh"
            }
        }
    }
}
