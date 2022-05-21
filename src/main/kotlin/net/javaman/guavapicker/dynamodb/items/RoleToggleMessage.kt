package net.javaman.guavapicker.dynamodb.items

import aws.sdk.kotlin.services.dynamodb.model.AttributeValue
import dev.kord.common.entity.Snowflake
import net.javaman.guavapicker.dynamodb.asAttribute
import net.javaman.guavapicker.dynamodb.getList
import net.javaman.guavapicker.dynamodb.getNullableString
import net.javaman.guavapicker.dynamodb.getSnowflake
import net.javaman.guavapicker.dynamodb.getString

data class RoleToggleMessage(
    val messageId: Snowflake,
    val buttons: List<RoleToggleButton> = emptyList(),
    val header: String? = null,
    val body: String? = null
) : Item {
    companion object {
        fun fromItem(messageId: Snowflake, item: Map<String, AttributeValue>) = RoleToggleMessage(
            messageId = messageId,
            buttons = item.getList("buttons") { RoleToggleButton.fromItem((it as AttributeValue.M).value) },
            header = item.getNullableString("header"),
            body = item.getNullableString("body")
        )
    }

    override fun toItem() = mapOf(
        messageId asAttribute "message_id",
        buttons asAttribute "buttons",
        header asAttribute "header",
        body asAttribute "body"
    )
}

data class RoleToggleButton(
    val roleId: Snowflake,
    val label: String,
    val emoji: String? = null
) : Item {
    companion object {
        fun fromItem(item: Map<String, AttributeValue>) = RoleToggleButton(
            roleId = item.getSnowflake("role_id"),
            label = item.getString("label"),
            emoji = item.getNullableString("emoji")
        )
    }

    override fun toItem() = mapOf(
        roleId asAttribute "role_id",
        label asAttribute "label",
        emoji asAttribute "emoji"
    )
}