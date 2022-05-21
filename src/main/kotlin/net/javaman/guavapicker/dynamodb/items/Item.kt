package net.javaman.guavapicker.dynamodb.items

import aws.sdk.kotlin.services.dynamodb.model.AttributeValue

interface Item {
    fun toItem(): Map<String, AttributeValue>
}