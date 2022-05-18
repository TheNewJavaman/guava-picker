package net.javaman.guavapicker.dynamodb.tables

import aws.sdk.kotlin.services.dynamodb.model.AttributeValue
import dev.kord.common.entity.Snowflake
import net.javaman.guavapicker.dynamodb.ddbClient
import net.javaman.guavapicker.dynamodb.items.RoleToggleMessage

object RoleToggleMessages {
    private const val TABLE_NAME = "role_toggle_messages"

    suspend fun put(item: RoleToggleMessage) {
        ddbClient.putItem {
            tableName = TABLE_NAME
            this.item = item.toItem()
        }
    }

    suspend fun get(messageId: Snowflake) = ddbClient.getItem {
        tableName = TABLE_NAME
        key = mutableMapOf("message_id" to AttributeValue.N(messageId.value.toString()))
        projectionExpression = "buttons, header, body"
    }.let { RoleToggleMessage.fromItem(messageId, it.item!!) }
}