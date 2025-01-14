package info.metadude.kotlin.library.roomstates.base

import com.google.common.truth.Truth.assertThat
import info.metadude.kotlin.library.roomstates.base.models.Room
import info.metadude.kotlin.library.roomstates.base.models.State.*
import kotlinx.coroutines.test.runTest
import okhttp3.OkHttpClient
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.fail
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class RoomStatesServiceMockedBackendTest {

    private lateinit var server: MockWebServer
    private lateinit var service: RoomStatesService

    @BeforeEach
    fun setup() {
        server = MockWebServer().apply { start() }
        service = createService()
    }

    @AfterEach
    fun tearDown() {
        server.shutdown()
    }

    @Test
    fun `getRooms parses JSON with mutable rooms and states`() = runTest {
        val json = """
            [
                {"roomname": "Room A", "state": 0},
                {"roomname": "Room B", "state": 1},
                {"roomname": "Room C", "state": 2},
                {"roomname": "Room D", "state": -1}
            ]
        """.trimIndent()

        server.enqueue(createResponse(json))
        service.getRooms("")
            .onSuccess { assertRooms(it) }
            .onFailure { fail("Should not fail with $it") }
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
    fun `getRooms parses JSON without any rooms and states`() = runTest {
        val json = """
            []
        """.trimIndent()

        server.enqueue(createResponse(json))
        service.getRooms("")
            .onSuccess { assertThat(it).hasSize(0) }
            .onFailure { fail("Should not fail with $it") }
    }

    @Test
    fun `getRooms throws exception when JSON is empty`() = runTest {
        val json = """
        """.trimIndent()

        server.enqueue(createResponse(json))
        assertThat(service.getRooms("").isFailure).isTrue()
    }

    @Test
    fun `getRooms throws exception when JSON is malformed`() = runTest {
        val json = """
            {}
        """.trimIndent()

        server.enqueue(createResponse(json))
        assertThat(service.getRooms("").isFailure).isTrue()
    }

    private fun createResponse(json: String) = MockResponse()
        .setBody(json)
        .addHeader("Content-Type", "application/json; charset=UTF-8")

    private fun createService() = Api
        .createRetrofit(
            baseUrl = server.url("/").toString(),
            callFactory = OkHttpClient.Builder().build()
        )
        .create(RoomStatesService::class.java)

}
