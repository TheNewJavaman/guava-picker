package net.javaman.guavapicker.sql.pickerroles

import net.javaman.guavapicker.sql.ATable
import net.javaman.guavapicker.sql.pickers.Pickers
import net.javaman.guavapicker.sql.snowflake

object PickerRoles : ATable("picker_roles") {
    val pickerId = reference("picker_id", Pickers)
    val roleId = snowflake("role_id").index()
}