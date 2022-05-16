package net.javaman.guavapicker.models

import dev.kord.common.entity.Snowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class ToggleRoleButtonModel(
    @SerialName("r")
    val role: Snowflake
) : OCustomIdModel(CustomId.TOGGLE_ROLE_BUTTON)