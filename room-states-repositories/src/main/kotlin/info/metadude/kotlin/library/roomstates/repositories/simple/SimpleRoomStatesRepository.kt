package info.metadude.kotlin.library.roomstates.repositories.simple

import info.metadude.kotlin.library.roomstates.base.RoomStatesApi
import info.metadude.kotlin.library.roomstates.base.models.Room
import info.metadude.kotlin.library.roomstates.repositories.RoomStatesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.Call

class SimpleRoomStatesRepository(
    private val url: String,
    private val path: String,
    private val httpClient: Call.Factory,
    private val api: RoomStatesApi,
) : RoomStatesRepository {

    override suspend fun getRooms(): Flow<Result<List<Room>>> {
        return flow {
            try {
                val response = api
                    .provideRoomStatesService(url, httpClient)
                    .getRooms(path)
                emit(response)
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }
    }

}