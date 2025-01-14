package info.metadude.kotlin.library.roomstates.base.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Room(
    @SerialName("roomname")
    val name: String,
    val state: State,
)
