package info.metadude.kotlin.library.roomstates.repositories.services

import info.metadude.kotlin.library.roomstates.base.RoomStatesService
import info.metadude.kotlin.library.roomstates.base.models.Room

class ImmediatelyFailingService : RoomStatesService {

    override suspend fun getRooms(path: String): Result<List<Room>> = Result.failure(ServiceUnavailableException())

}

class ServiceUnavailableException : Exception("Service unavailable.")
