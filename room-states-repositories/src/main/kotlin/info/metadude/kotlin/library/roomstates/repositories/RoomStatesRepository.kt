package info.metadude.kotlin.library.roomstates.repositories

import info.metadude.kotlin.library.roomstates.base.models.Room
import kotlinx.coroutines.flow.Flow

interface RoomStatesRepository {

    suspend fun getRooms(): Flow<Result<List<Room>>>

}
