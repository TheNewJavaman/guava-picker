package net.javaman.guavapicker.sql.pickers

import dev.kord.common.entity.Snowflake
import net.javaman.guavapicker.sql.AEntity
import net.javaman.guavapicker.sql.AEntityClass
import net.javaman.guavapicker.sql.pickerroles.PickerRole
import net.javaman.guavapicker.sql.pickerroles.PickerRoles
import org.jetbrains.exposed.dao.id.EntityID

class Picker(id: EntityID<ULong>) : AEntity(id, Pickers) {
    companion object : AEntityClass<Picker>(Pickers) {
        fun findByMessageId(messageId: Snowflake) = find {
            Pickers.messageId eq messageId
        }
    }

    var messageId by Pickers.messageId
    var message by Pickers.message
}