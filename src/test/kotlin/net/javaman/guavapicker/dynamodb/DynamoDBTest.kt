package net.javaman.guavapicker.dynamodb

import aws.sdk.kotlin.runtime.endpoint.AwsEndpoint
import aws.sdk.kotlin.runtime.endpoint.StaticEndpointResolver
import aws.sdk.kotlin.services.dynamodb.DynamoDbClient
import dev.kord.common.entity.Snowflake
import kotlin.test.assertEquals
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.Clock.System.now
import net.javaman.guavapicker.dynamodb.items.RtbButtonItem
import net.javaman.guavapicker.dynamodb.items.RtbMessageItem
import net.javaman.guavapicker.dynamodb.roletogglebutton.RtbMessagesTable
import org.junit.jupiter.api.Test

class DynamoDBTest {
    @Test
    fun putAndGet() = runBlocking {
        ddbClient = DynamoDbClient {
            endpointResolver = StaticEndpointResolver(AwsEndpoint("http://localhost:8000"))
            region = "us-west-1"
        }
        val messageId = Snowflake(now())
        val original = RtbMessageItem(
            messageId,
            List(3) {
                RtbButtonItem(
                    Snowflake(now()),
                    "Label goes here"
                    // Intentionally null emoji string
                )
            },
            "Header goes here",
            // Intentionally null body string
        )
        RtbMessagesTable.put(original)
        val saved = RtbMessagesTable.get(messageId)
        assertEquals(original, saved)
    }
}