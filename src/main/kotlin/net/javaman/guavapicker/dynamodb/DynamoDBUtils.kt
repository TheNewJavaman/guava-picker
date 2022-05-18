package net.javaman.guavapicker.dynamodb

import aws.sdk.kotlin.services.dynamodb.DynamoDbClient
import aws.sdk.kotlin.services.dynamodb.model.AttributeValue
import dev.kord.common.entity.Snowflake

lateinit var ddbClient: DynamoDbClient

fun AttributeValue.asSnowflake() = Snowflake((this as AttributeValue.S).value)

fun AttributeValue.asString() = (this as AttributeValue.S).value

fun AttributeValue.asNullableString() = (this as AttributeValue.S).value.let { if (it == "") null else it }

fun AttributeValue.asList() = (this as AttributeValue.L).value

fun AttributeValue.asMap() = (this as AttributeValue.M).value
