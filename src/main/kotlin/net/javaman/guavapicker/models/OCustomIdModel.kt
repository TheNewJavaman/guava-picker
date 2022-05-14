package net.javaman.guavapicker.models

import kotlinx.serialization.KSerializer
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.descriptors.PrimitiveKind
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

@Serializable
open class OCustomIdModel(
    @SerialName("i")
    val id: CustomId
)

/**
 * Don't change the id/don't use ordinal unless you want to break previous buttons
 */
@Serializable(with = CustomIdSerializer::class)
enum class CustomId(val id: Int) {
    ROLE_BUTTON(1);

    companion object {
        fun valueOf(id: Int) = when (id) {
            ROLE_BUTTON.id -> ROLE_BUTTON
            else -> throw IllegalStateException("CustomId(id=$id) does not exist")
        }
    }
}

object CustomIdSerializer : KSerializer<CustomId> {
    override val descriptor = PrimitiveSerialDescriptor("CustomId", PrimitiveKind.INT)

    override fun deserialize(decoder: Decoder) = CustomId.valueOf(decoder.decodeInt())

    override fun serialize(encoder: Encoder, value: CustomId) = encoder.encodeInt(value.id)
}