package net.javaman.guavapicker.dynamodb

import aws.sdk.kotlin.runtime.endpoint.AwsEndpoint
import aws.sdk.kotlin.runtime.endpoint.StaticEndpointResolver
import aws.sdk.kotlin.services.dynamodb.DynamoDbClient
import dev.kord.common.entity.Snowflake
import kotlin.test.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.Clock.System.now
import net.javaman.guavapicker.dynamodb.items.RoleToggleButton
import net.javaman.guavapicker.dynamodb.items.RoleToggleMessage
import net.javaman.guavapicker.dynamodb.tables.RoleToggleMessagesTable
import org.junit.jupiter.api.Test

class DynamoDBTest {
    @Test
    fun putAndGet() = runBlocking {
        ddbClient = DynamoDbClient {
            endpointResolver = StaticEndpointResolver(AwsEndpoint("http://localhost:8000"))
            region = "us-west-1"
        }
        val messageId = Snowflake(now())
        val original = RoleToggleMessage(
            messageId,
            List(3) {
                RoleToggleButton(
                    Snowflake(now()),
                    "Label goes here"
                    // Intentionally null emoji string
                )
            },
            "Header goes here",
            // Intentionally null body string
        )
        RoleToggleMessagesTable.put(original)
        val saved = RoleToggleMessagesTable.get(messageId)
        assertEquals(original, saved)
    }
}