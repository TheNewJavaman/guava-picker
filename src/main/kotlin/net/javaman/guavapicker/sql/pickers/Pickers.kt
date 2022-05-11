package net.javaman.guavapicker.sql.pickers

import net.javaman.guavapicker.sql.ATable
import net.javaman.guavapicker.sql.snowflake

object Pickers : ATable("pickers") {
    val messageId = snowflake("message_id").index()
    val message = text("message")
}