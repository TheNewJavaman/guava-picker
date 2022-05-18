package net.javaman.guavapicker

import aws.sdk.kotlin.runtime.endpoint.AwsEndpoint
import aws.sdk.kotlin.runtime.endpoint.StaticEndpointResolver
import aws.sdk.kotlin.services.dynamodb.DynamoDbClient
import dev.kord.common.entity.Snowflake
import dev.kord.core.event.interaction.GuildButtonInteractionCreateEvent
import dev.kord.core.event.interaction.GuildChatInputCommandInteractionCreateEvent
import dev.kord.core.event.interaction.GuildMessageCommandInteractionCreateEvent
import dev.kord.core.on
import kotlinx.coroutines.runBlocking
import net.javaman.guavapicker.dynamodb.ddbClient
import net.javaman.guavapicker.old.events.ButtonEvent
import net.javaman.guavapicker.old.events.ChatInputCommandEvent
import net.javaman.guavapicker.old.events.MessageCommandEvent
import net.javaman.guavapicker.old.interactions.AddRoleSelectMenuInteraction
import net.javaman.guavapicker.old.interactions.CreateCommandInteraction
import org.junit.jupiter.api.Test

class MainTest {
    companion object {
        private val guild = Snowflake(972987144735952916)
    }

    @Test
    fun main() = runBlocking {
        ddbClient = DynamoDbClient {
            endpointResolver = StaticEndpointResolver(AwsEndpoint("http://localhost:8000"))
            region = "us-west-1"
        }
        startDiscord {
            createGuildChatInputCommand(
                guild,
                CreateCommandInteraction.name,
                CreateCommandInteraction.description,
                CreateCommandInteraction.builder
            )
            createGuildMessageCommand(guild, AddRoleSelectMenuInteraction.name, AddRoleSelectMenuInteraction.builder)
            on<GuildChatInputCommandInteractionCreateEvent>(this, ChatInputCommandEvent.handler)
            on<GuildButtonInteractionCreateEvent>(this, ButtonEvent.handler)
            on<GuildMessageCommandInteractionCreateEvent>(this, MessageCommandEvent.handler)
        }
    }
}