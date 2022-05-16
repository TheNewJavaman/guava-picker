package net.javaman.guavapicker

import dev.kord.core.event.interaction.GlobalButtonInteractionCreateEvent
import dev.kord.core.event.interaction.GlobalChatInputCommandInteractionCreateEvent
import dev.kord.core.event.interaction.GlobalMessageCommandInteractionCreateEvent
import dev.kord.core.on
import net.javaman.guavapicker.events.ButtonEvent
import net.javaman.guavapicker.events.ChatInputCommandEvent
import net.javaman.guavapicker.events.MessageCommandEvent
import net.javaman.guavapicker.interactions.AddRoleSelectMenuInteraction
import net.javaman.guavapicker.interactions.CreateCommandInteraction

suspend fun main() = startDiscord {
    createGlobalChatInputCommand(CreateCommandInteraction.name, CreateCommandInteraction.description, CreateCommandInteraction.builder)
    createGlobalMessageCommand(AddRoleSelectMenuInteraction.name, AddRoleSelectMenuInteraction.builder)
    on<GlobalChatInputCommandInteractionCreateEvent>(this, ChatInputCommandEvent.handler)
    on<GlobalButtonInteractionCreateEvent>(this, ButtonEvent.handler)
    on<GlobalMessageCommandInteractionCreateEvent>(this, MessageCommandEvent.handler)
}