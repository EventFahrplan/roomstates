package info.metadude.kotlin.library.roomstates.repositories.simple

import info.metadude.kotlin.library.roomstates.base.Api
import info.metadude.kotlin.library.roomstates.base.RoomStatesApi
import info.metadude.kotlin.library.roomstates.base.models.Room
import info.metadude.kotlin.library.roomstates.repositories.RoomStatesRepository
import info.metadude.kotlin.library.roomstates.repositories.utils.UrlComponents.Companion.getUrlComponents
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import okhttp3.Call
import okhttp3.OkHttpClient

class SimpleRoomStatesRepository(
    private val callFactory: Call.Factory = OkHttpClient.Builder().build(),
    private val api: RoomStatesApi = Api,
) : RoomStatesRepository {

    override suspend fun getRooms(url: String): Flow<Result<List<Room>>> {
        return flow {
            try {
                val (baseUrl, path) = url.getUrlComponents()
                val response = api
                    .provideRoomStatesService(baseUrl, callFactory)
                    .getRooms(path)
                emit(response)
            } catch (e: Exception) {
                emit(Result.failure(e))
            }
        }
    }

}