package net.javaman.guavapicker.sql.editors

import net.javaman.guavapicker.sql.ATable
import net.javaman.guavapicker.sql.pickers.Pickers
import net.javaman.guavapicker.sql.snowflake

object Editors : ATable("editors") {
    val messageId = snowflake("message_id").index()
    val pickerId = reference("picker_id", Pickers)
}