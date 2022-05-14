package net.javaman.guavapicker

import dev.kord.core.event.interaction.GlobalChatInputCommandInteractionCreateEvent
import dev.kord.core.on
import net.javaman.guavapicker.events.ChatInputCommandEvent
import net.javaman.guavapicker.interactions.CreateInteraction

suspend fun main() {
    val kord = startDiscord()
    kord.createGlobalChatInputCommand(CreateInteraction.name, CreateInteraction.description, CreateInteraction.builder)
    kord.on<GlobalChatInputCommandInteractionCreateEvent>(kord, ChatInputCommandEvent.handle)
    logIntoDiscord(kord)
}