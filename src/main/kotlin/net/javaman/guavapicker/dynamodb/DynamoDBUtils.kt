package net.javaman.guavapicker.dynamodb

import aws.sdk.kotlin.services.dynamodb.DynamoDbClient
import aws.sdk.kotlin.services.dynamodb.model.AttributeValue
import dev.kord.common.entity.Snowflake
import net.javaman.guavapicker.dynamodb.items.Item

lateinit var ddbClient: DynamoDbClient

fun Map<String, AttributeValue>.getSnowflake(key: String) = Snowflake((get(key)!! as AttributeValue.S).value)

fun Map<String, AttributeValue>.getString(key: String) = (get(key)!! as AttributeValue.S).value

fun Map<String, AttributeValue>.getNullableString(key: String) =
    (get(key)!! as AttributeValue.S).value.let { if (it == "") null else it }

fun <T> Map<String, AttributeValue>.getList(key: String, block: (AttributeValue) -> T) =
    (get(key)!! as AttributeValue.L).value.map(block)

fun Map<String, AttributeValue>.getMap(key: String) = (get(key)!! as AttributeValue.M).value

infix fun Snowflake.asAttribute(key: String) = key to AttributeValue.S(value.toString())

infix fun String?.asAttribute(key: String) = key to AttributeValue.S(this ?: "")

infix fun List<Item>.asAttribute(key: String) = key to AttributeValue.L(map { AttributeValue.M(it.toItem()) })
