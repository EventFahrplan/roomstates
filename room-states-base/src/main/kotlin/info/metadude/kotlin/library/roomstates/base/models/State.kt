package info.metadude.kotlin.library.roomstates.base.models

import info.metadude.kotlin.library.roomstates.base.serializers.StateSerializer
import kotlinx.serialization.Serializable

@Serializable(with = StateSerializer::class)
enum class State {
    UNKNOWN,
    EMPTY,
    FULL,
    TOO_FULL;
}
