package net.javaman.guavapicker.models

import dev.kord.common.entity.Snowflake
import kotlinx.serialization.Serializable

@Serializable
data class RoleButtonCustomId(
    val role: Snowflake,
    val label: String
) : ICustomId {
    override val type = ICustomId.Type.ROLE_BUTTON_MODEL
}

