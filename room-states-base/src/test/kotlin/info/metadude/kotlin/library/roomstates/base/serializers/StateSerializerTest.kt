package info.metadude.kotlin.library.roomstates.base.serializers

import com.google.common.truth.Truth.assertThat
import info.metadude.kotlin.library.roomstates.base.models.State
import info.metadude.kotlin.library.roomstates.base.models.State.*
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.int
import kotlinx.serialization.json.jsonPrimitive
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EnumSource

class StateSerializerTest {

    private val json = Json

    @ParameterizedTest
    @EnumSource(State::class)
    fun `serialize return corresponding integer value`(state: State) {
        val expected = when (state) {
            EMPTY -> 0
            FULL -> 1
            TOO_FULL -> 2
            UNKNOWN -> Int.MIN_VALUE
        }
        val actual = json.encodeToJsonElement(StateSerializer, state).jsonPrimitive.int
        assertThat(actual).isEqualTo(expected)
    }

    @ParameterizedTest
    @EnumSource(State::class)
    fun `deserialize return corresponding State value`(expected: State) {
        val intValue = when (expected) {
            EMPTY -> 0
            FULL -> 1
            TOO_FULL -> 2
            UNKNOWN -> -1
        }
        val actual = json.decodeFromString(StateSerializer, "$intValue")
        assertThat(actual).isEqualTo(expected)
    }

}
