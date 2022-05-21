package net.javaman.guavapicker

import aws.sdk.kotlin.runtime.endpoint.AwsEndpoint
import aws.sdk.kotlin.runtime.endpoint.StaticEndpointResolver
import aws.sdk.kotlin.services.dynamodb.DynamoDbClient
import dev.kord.common.entity.Snowflake
import dev.kord.core.on
import kotlinx.coroutines.runBlocking
import net.javaman.guavapicker.discord.handlers.chatInputCommandHandler
import net.javaman.guavapicker.discord.interactions.CreateInteraction
import net.javaman.guavapicker.discord.startDiscord
import net.javaman.guavapicker.dynamodb.ddbClient
import org.junit.jupiter.api.Test

class MainTest {
    companion object {
        private val guild = Snowflake(972987144735952916)
    }

    @Test
    fun test() = runBlocking {
        ddbClient = DynamoDbClient {
            endpointResolver = StaticEndpointResolver(AwsEndpoint("http://localhost:8000"))
            region = "us-west-1"
        }
        startDiscord {
            createGuildChatInputCommand(
                guild,
                CreateInteraction.NAME,
                CreateInteraction.DESCRIPTION,
                CreateInteraction.builder
            )
            on(this, chatInputCommandHandler)
        }
    }
}