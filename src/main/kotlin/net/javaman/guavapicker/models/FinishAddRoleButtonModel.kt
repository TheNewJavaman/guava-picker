package net.javaman.guavapicker.models

import dev.kord.common.entity.Snowflake
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class FinishAddRoleButtonModel(
    @SerialName("r")
    val role: Snowflake
) : OCustomIdModel(CustomId.FINISH_ADD_ROLE_BUTTON)