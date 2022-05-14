package net.javaman.guavapicker.models

interface ICustomId {
    val type: Type

    enum class Type {
        ROLE_BUTTON_MODEL
    }
}
