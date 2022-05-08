@file:OptIn(ExperimentalUnsignedTypes::class)

package net.javaman.guavapicker

import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp

object PickerMessages : Table("picker_messages") {
    val id = ulong("id").autoIncrement()
    val messageId = ulong("message_id")
    val header = text("header")
    val createdAt = timestamp("created_at")
    val updatedAt = timestamp("updated_at")
    override val primaryKey = PrimaryKey(id)
}

object PickerMessageRoles : Table("picker_message_roles") {
    val id = ulong("id").autoIncrement()
    val pickerMessageId = ulong("picker_message_id").references(PickerMessages.id).index()
    val roleId = ulong("role_id")
    val createdAt = timestamp("created_at")
    val updatedAt = timestamp("updated_at")
}