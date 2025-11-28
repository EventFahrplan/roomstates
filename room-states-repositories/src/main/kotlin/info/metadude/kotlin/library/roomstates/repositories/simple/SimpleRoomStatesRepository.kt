package info.metadude.kotlin.library.roomstates.repositories.simple

import info.metadude.kotlin.library.roomstates.base.Api
import info.metadude.kotlin.library.roomstates.base.RoomStatesApi
import info.metadude.kotlin.library.roomstates.base.models.Room
import info.metadude.kotlin.library.roomstates.repositories.RoomStatesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.Call
import okhttp3.OkHttpClient

class SimpleRoomStatesRepository(
    private val url: String,
    private val path: String,
    private val callFactory: Call.Factory = OkHttpClient.Builder().build(),
    private val api: RoomStatesApi = Api,
) : RoomStatesRepository {

    override suspend fun getRooms(): Flow<Result<List<Room>>> {
        return flow {
            try {
                val response = api
                    .provideRoomStatesService(url, callFactory)
                    .getRooms(path)
                emit(response)
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }
    }

}