package net.javaman.guavapicker.sql.pickerroles

import dev.kord.common.entity.Snowflake
import net.javaman.guavapicker.sql.AEntity
import net.javaman.guavapicker.sql.AEntityClass
import net.javaman.guavapicker.sql.pickers.Picker
import org.jetbrains.exposed.dao.id.EntityID

class PickerRole(id: EntityID<ULong>) : AEntity(id, PickerRoles) {
    companion object : AEntityClass<PickerRole>(PickerRoles) {
        fun findByRoleId(roleId: Snowflake) = PickerRole.find {
            PickerRoles.roleId eq roleId
        }

        fun findByPicker(picker: Picker) = PickerRole.find {
            PickerRoles.pickerId eq picker.id
        }
    }

    var picker by Picker referencedOn PickerRoles.pickerId
    var roleId by PickerRoles.roleId
}