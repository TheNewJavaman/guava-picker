package net.javaman.guavapicker.dynamodb.items

import aws.sdk.kotlin.services.dynamodb.model.AttributeValue
import dev.kord.common.entity.Snowflake
import net.javaman.guavapicker.dynamodb.asList
import net.javaman.guavapicker.dynamodb.asMap
import net.javaman.guavapicker.dynamodb.asNullableString
import net.javaman.guavapicker.dynamodb.asSnowflake
import net.javaman.guavapicker.dynamodb.asString

data class RoleToggleMessage(
    val messageId: Snowflake,
    val buttons: List<RoleToggleButton> = emptyList(),
    val header: String? = null,
    val body: String? = null
) {
    companion object {
        fun fromItem(messageId: Snowflake, item: Map<String, AttributeValue>) = RoleToggleMessage(
            messageId = messageId,
            buttons = item["buttons"]!!.asList().map { RoleToggleButton.fromItem(it.asMap()) },
            header = item["header"]!!.asNullableString(),
            body = item["body"]!!.asNullableString()
        )
    }

    fun toItem() = mutableMapOf(
        "message_id" to AttributeValue.S(messageId.value.toString()),
        "buttons" to AttributeValue.L(buttons.map { AttributeValue.M(it.toItem()) }),
        "header" to AttributeValue.S(header ?: ""),
        "body" to AttributeValue.S(body ?: "")
    )
}

data class RoleToggleButton(
    val roleId: Snowflake,
    val label: String,
    val emoji: String? = null
) {
    companion object {
        fun fromItem(item: Map<String, AttributeValue>) = RoleToggleButton(
            roleId = item["role_id"]!!.asSnowflake(),
            label = item["label"]!!.asString(),
            emoji = item["emoji"]!!.asNullableString()
        )
    }

    fun toItem() = mutableMapOf(
        "role_id" to AttributeValue.S(roleId.value.toString()),
        "label" to AttributeValue.S(label),
        "emoji" to AttributeValue.S(emoji ?: "")
    )
}