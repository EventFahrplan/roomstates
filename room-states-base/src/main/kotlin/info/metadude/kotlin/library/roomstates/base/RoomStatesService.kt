package info.metadude.kotlin.library.roomstates.base

import info.metadude.kotlin.library.roomstates.base.models.Room
import retrofit2.http.GET
import retrofit2.http.Path

interface RoomStatesService {

    @GET("{path}")
    suspend fun getRooms(
        @Path("path", encoded = true) path: String,
    ): Result<List<Room>>

}
