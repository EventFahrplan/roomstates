package info.metadude.kotlin.library.roomstates.base.serializers

import info.metadude.kotlin.library.roomstates.base.models.State
import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.PrimitiveKind.INT
import kotlinx.serialization.descriptors.PrimitiveSerialDescriptor
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

internal object StateSerializer : KSerializer<State> {

    override val descriptor: SerialDescriptor = PrimitiveSerialDescriptor("State", INT)

    override fun deserialize(decoder: Decoder): State = when (decoder.decodeInt()) {
        0 -> State.EMPTY
        1 -> State.FULL
        2 -> State.TOO_FULL
        else -> State.UNKNOWN
    }

    override fun serialize(encoder: Encoder, value: State) = when (value) {
        State.EMPTY -> encoder.encodeInt(0)
        State.FULL -> encoder.encodeInt(1)
        State.TOO_FULL -> encoder.encodeInt(2)
        State.UNKNOWN -> encoder.encodeInt(Int.MIN_VALUE)
    }

}
