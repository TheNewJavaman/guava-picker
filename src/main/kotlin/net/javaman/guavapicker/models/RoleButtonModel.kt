package net.javaman.guavapicker.models

import dev.kord.common.entity.Snowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class RoleButtonModel(
    @SerialName("r")
    val role: Snowflake,

    @SerialName("l")
    val label: String
) : OCustomIdModel(CustomId.ROLE_BUTTON)