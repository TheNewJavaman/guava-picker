package net.javaman.guavapicker.sql

import dev.kord.common.entity.Snowflake
import org.jetbrains.exposed.sql.Column
import org.jetbrains.exposed.sql.ColumnType
import org.jetbrains.exposed.sql.Table
import org.jetbrains.exposed.sql.statements.api.PreparedStatementApi
import org.jetbrains.exposed.sql.vendors.currentDialect

/**
 * @see [org.jetbrains.exposed.sql.ULongColumnType]
 */
class SnowflakeColumnType : ColumnType() {
    override fun sqlType(): String = currentDialect.dataTypeProvider.ulongType()

    override fun valueFromDB(value: Any): Snowflake {
        if (value is Snowflake) return value
        val v = when (value) {
            is ULong -> value
            is Long -> value.takeIf { it >= 0 }?.toULong()
            is Number -> value.toLong().takeIf { it >= 0 }?.toULong()
            is String -> value.toULong()
            else -> error("Unexpected value: $value of ${value::class.qualifiedName}")
        } ?: error("Negative value but type is ULong: $value")
        return Snowflake(v)
    }

    override fun setParameter(stmt: PreparedStatementApi, index: Int, value: Any?) {
        val v = if (value is Snowflake) value.value.toLong() else value
        super.setParameter(stmt, index, v)
    }

    override fun notNullValueToDB(value: Any): Any {
        val v = if (value is Snowflake) value.value.toLong() else value
        return super.notNullValueToDB(v)
    }
}

fun Table.snowflake(name: String): Column<Snowflake> = registerColumn(name, SnowflakeColumnType())