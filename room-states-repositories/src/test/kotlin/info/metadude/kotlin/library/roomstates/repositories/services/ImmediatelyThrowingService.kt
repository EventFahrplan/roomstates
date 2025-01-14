package info.metadude.kotlin.library.roomstates.repositories.services

import info.metadude.kotlin.library.roomstates.base.RoomStatesService
import info.metadude.kotlin.library.roomstates.base.models.Room

class ImmediatelyThrowingService : RoomStatesService {

    override suspend fun getRooms(path: String): Result<List<Room>> = throw RuntimeException()

}
