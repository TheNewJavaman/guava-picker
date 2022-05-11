package net.javaman.guavapicker.sql

import kotlinx.datetime.Clock
import org.jetbrains.exposed.dao.id.IdTable
import org.jetbrains.exposed.sql.kotlin.datetime.timestamp

@OptIn(ExperimentalUnsignedTypes::class)
open class ATable(name: String) : IdTable<ULong>(name) {
    final override val id = ulong("id").autoIncrement().entityId()
    val createdAt = timestamp("created_at").clientDefault { Clock.System.now() }
    val updatedAt = timestamp("updated_at").clientDefault { Clock.System.now() }
    override val primaryKey = PrimaryKey(id)
}