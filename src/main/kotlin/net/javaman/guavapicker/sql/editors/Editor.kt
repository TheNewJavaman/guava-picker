package net.javaman.guavapicker.sql.editors

import dev.kord.common.entity.Snowflake
import net.javaman.guavapicker.sql.AEntity
import net.javaman.guavapicker.sql.AEntityClass
import net.javaman.guavapicker.sql.pickers.Picker
import org.jetbrains.exposed.dao.id.EntityID

class Editor(id: EntityID<ULong>) : AEntity(id, Editors) {
    companion object : AEntityClass<Editor>(Editors) {
        fun findByMessageId(messageId: Snowflake) = Editor.find {
            Editors.messageId eq messageId
        }
    }

    var messageId by Editors.messageId
    var picker by Picker referencedOn Editors.pickerId
}