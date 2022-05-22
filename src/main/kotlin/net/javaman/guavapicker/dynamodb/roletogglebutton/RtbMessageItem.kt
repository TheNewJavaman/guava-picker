package net.javaman.guavapicker.dynamodb.items

import aws.sdk.kotlin.services.dynamodb.model.AttributeValue
import dev.kord.common.entity.Snowflake
import net.javaman.guavapicker.dynamodb.asAttribute
import net.javaman.guavapicker.dynamodb.getList
import net.javaman.guavapicker.dynamodb.getNullableString
import net.javaman.guavapicker.dynamodb.getSnowflake
import net.javaman.guavapicker.dynamodb.getString
import net.javaman.guavapicker.dynamodb.toAttribute

data class RtbMessageItem(
    val messageId: Snowflake,
    val buttons: List<RtbButtonItem> = emptyList(),
    val header: String? = null,
    val body: String? = null
) {
    companion object {
        fun fromItem(messageId: Snowflake, item: Map<String, AttributeValue>) = RtbMessageItem(
            messageId = messageId,
            buttons = item.getList("buttons") { RtbButtonItem.fromItem((it as AttributeValue.M).value) },
            header = item.getNullableString("header"),
            body = item.getNullableString("body")
        )
    }

    fun toItem() = mapOf(
        messageId asAttribute "message_id",
        buttons.map { it.toItem().toAttribute() } asAttribute "buttons",
        header asAttribute "header",
        body asAttribute "body"
    )
}

data class RtbButtonItem(
    val roleId: Snowflake,
    val label: String,
    val emoji: String? = null,
    val addedConfirmation: String? = null,
    val removedConfirmation: String? = null,
) {
    companion object {
        fun fromItem(item: Map<String, AttributeValue>) = RtbButtonItem(
            roleId = item.getSnowflake("role_id"),
            label = item.getString("label"),
            emoji = item.getNullableString("emoji"),
            addedConfirmation = item.getNullableString("added_confirmation"),
            removedConfirmation = item.getNullableString("removed_confirmation")
        )
    }

    fun toItem() = mapOf(
        roleId asAttribute "role_id",
        label asAttribute "label",
        emoji asAttribute "emoji",
        addedConfirmation asAttribute "added_confirmation",
        removedConfirmation asAttribute "removed_confirmation"
    )
}