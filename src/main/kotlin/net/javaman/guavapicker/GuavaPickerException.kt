package net.javaman.guavapicker

class GuavaPickerException(
    override val message: String? = null,
    override val cause: Throwable? = null
) : Exception()