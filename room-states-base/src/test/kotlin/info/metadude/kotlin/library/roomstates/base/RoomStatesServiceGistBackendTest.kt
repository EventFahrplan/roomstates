package info.metadude.kotlin.library.roomstates.base

import com.google.common.truth.Truth.assertThat
import info.metadude.kotlin.library.roomstates.base.models.Room
import info.metadude.kotlin.library.roomstates.base.models.State.*
import kotlinx.coroutines.test.runTest
import kotlinx.serialization.SerializationException
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.Test

class RoomStatesServiceGistBackendTest {

    private companion object {
        const val BASE_URL = "https://gist.githubusercontent.com"
        const val URL_PART_PATH =
            "johnjohndoe/1f96ca8c2cecc44a05f28e28548d545d/raw/4f15259e9d5f2f58c919c408226007801bdef821/rooms.json"
    }

    @Test
    fun `getRooms responds successfully`() = runTest {
        try {
            service.getRooms(URL_PART_PATH)
                .onSuccess {
                    assertThat(it).isNotNull()
                    assertRooms(it)
                }
                .onFailure {
                    fail("Should not fail with $it")
                }
        } catch (t: Throwable) {
            fail("Should not throw $t")
        }
    }

    private fun assertRooms(rooms: List<Room>) {
        assertThat(rooms).hasSize(4)
        assertRoom(rooms[0], Room("Room A", EMPTY))
        assertRoom(rooms[1], Room("Room B", FULL))
        assertRoom(rooms[2], Room("Room C", TOO_FULL))
        assertRoom(rooms[3], Room("Room D", UNKNOWN))
    }

    private fun assertRoom(actualRoom: Room, expectedRoom: Room) {
        assertThat(actualRoom).isEqualTo(expectedRoom)
    }

    @Test
    fun `getRooms responds with an exception`() = runTest {
        try {
            service.getRooms("invalid")
                .onSuccess {
                    fail("Should not succeed with $it")
                }
                .onFailure {
                    assertThat(it).isInstanceOf(SerializationException::class.java)
                }
        } catch (t: Throwable) {
            fail("Should not throw $t")
        }
    }

    private val service: RoomStatesService by lazy {
        val interceptor = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
        val okHttpClient = OkHttpClient.Builder()
            .addNetworkInterceptor(interceptor)
            .build()
        Api.provideRoomStatesService(BASE_URL, okHttpClient)
    }

}
