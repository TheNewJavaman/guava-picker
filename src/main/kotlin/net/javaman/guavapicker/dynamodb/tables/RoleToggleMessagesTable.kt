package net.javaman.guavapicker.dynamodb.tables

import aws.sdk.kotlin.services.dynamodb.model.AttributeValue
import dev.kord.common.entity.Snowflake
import net.javaman.guavapicker.dynamodb.ddbClient
import net.javaman.guavapicker.dynamodb.items.RoleToggleMessage

object RoleToggleMessagesTable {
    private const val TABLE_NAME = "role_toggle_messages"

    suspend fun put(item: RoleToggleMessage) {
        ddbClient.putItem {
            tableName = TABLE_NAME
            this.item = item.toItem()
        }
    }

    suspend fun get(messageId: Snowflake) = ddbClient.getItem {
        tableName = TABLE_NAME
        key = mutableMapOf("message_id" to AttributeValue.S(messageId.value.toString()))
        projectionExpression = listOf("buttons", "header", "body").joinToString(", ")
    }.let { RoleToggleMessage.fromItem(messageId, it.item!!) }
}