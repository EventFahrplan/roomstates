package info.metadude.kotlin.library.roomstates.repositories.services

import info.metadude.kotlin.library.roomstates.base.RoomStatesService
import info.metadude.kotlin.library.roomstates.base.models.Room
import info.metadude.kotlin.library.roomstates.base.models.State

class ImmediatelySucceedingService : RoomStatesService {

    override suspend fun getRooms(path: String): Result<List<Room>> = Result.success(
        listOf(
            Room("Room 1", State.FULL)
        )
    )

}
