package net.javaman.guavapicker

import dev.kord.core.event.interaction.GlobalButtonInteractionCreateEvent
import dev.kord.core.event.interaction.GlobalChatInputCommandInteractionCreateEvent
import dev.kord.core.event.interaction.GlobalMessageCommandInteractionCreateEvent
import dev.kord.core.on
import net.javaman.guavapicker.discord.events.ButtonEvent
import net.javaman.guavapicker.discord.events.ChatInputCommandEvent
import net.javaman.guavapicker.discord.events.MessageCommandEvent
import net.javaman.guavapicker.discord.interactions.CreateInteraction
import net.javaman.guavapicker.discord.interactions.EditRolesInteraction
import net.javaman.guavapicker.discord.logIntoDiscord
import net.javaman.guavapicker.discord.startDiscord
import net.javaman.guavapicker.sql.startDatabase

suspend fun main() {
    startDatabase()

    val kord = startDiscord()
    kord.createGlobalChatInputCommand(CreateInteraction.name, CreateInteraction.description, CreateInteraction.builder)
    kord.createGlobalMessageCommand(EditRolesInteraction.name, EditRolesInteraction.builder)
    kord.on<GlobalChatInputCommandInteractionCreateEvent>(kord, ChatInputCommandEvent.handle)
    kord.on<GlobalMessageCommandInteractionCreateEvent>(kord, MessageCommandEvent.handle)
    kord.on<GlobalButtonInteractionCreateEvent>(kord, ButtonEvent.handle)
    logIntoDiscord(kord)
}