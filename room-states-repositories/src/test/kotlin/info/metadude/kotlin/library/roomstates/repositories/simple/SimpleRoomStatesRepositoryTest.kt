package info.metadude.kotlin.library.roomstates.repositories.simple

import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import info.metadude.kotlin.library.roomstates.base.RoomStatesApi
import info.metadude.kotlin.library.roomstates.base.models.Room
import info.metadude.kotlin.library.roomstates.base.models.State
import info.metadude.kotlin.library.roomstates.repositories.RoomStatesRepository
import info.metadude.kotlin.library.roomstates.repositories.services.ImmediatelyFailingService
import info.metadude.kotlin.library.roomstates.repositories.services.ImmediatelySucceedingService
import info.metadude.kotlin.library.roomstates.repositories.services.ImmediatelyThrowingService
import info.metadude.kotlin.library.roomstates.repositories.services.ServiceUnavailableException
import kotlinx.coroutines.test.runTest
import okhttp3.Call
import org.junit.jupiter.api.Test
import org.mockito.kotlin.doReturn
import org.mockito.kotlin.mock

class SimpleRoomStatesRepositoryTest {

    private lateinit var api: RoomStatesApi
    private val httpClient = mock<Call.Factory>()

    @Test
    fun `getRooms() returns success with list of rooms`() = runTest {
        api = mock {
            on { provideRoomStatesService("https://example.com", httpClient) }
                .doReturn(ImmediatelySucceedingService())
        }
        val repository = createRepository(api)
        repository.getRooms().test {
            val expected = listOf(Room("Room 1", State.FULL))
            assertThat(awaitItem()).isEqualTo(Result.success(expected))
            awaitComplete()
        }
    }

    @Test
    fun `getRooms() returns failure with service exception`() = runTest {
        api = mock {
            on { provideRoomStatesService("https://example.com", httpClient) }
                .doReturn(ImmediatelyFailingService())
        }
        val repository = createRepository(api)
        repository.getRooms().test {
            assertThat(awaitItem().exceptionOrNull()).isInstanceOf(ServiceUnavailableException::class.java)
            awaitComplete()
        }
    }

    @Test
    fun `getRooms() returns failure with runtime exception`() = runTest {
        api = mock {
            on { provideRoomStatesService("https://example.com", httpClient) }
                .doReturn(ImmediatelyThrowingService())
        }
        val repository = createRepository(api)
        repository.getRooms().test {
            assertThat(awaitItem().exceptionOrNull()).isInstanceOf(RuntimeException::class.java)
            awaitComplete()
        }
    }

    private fun createRepository(api: RoomStatesApi): RoomStatesRepository {
        return SimpleRoomStatesRepository(
            url = "https://example.com",
            path = "rooms",
            callFactory = httpClient,
            api = api,
        )
    }

}
