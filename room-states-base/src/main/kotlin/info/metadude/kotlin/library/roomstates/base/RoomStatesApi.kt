package info.metadude.kotlin.library.roomstates.base

import okhttp3.Call

interface RoomStatesApi {

    fun provideRoomStatesService(
        baseUrl: String,
        callFactory: Call.Factory,
    ): RoomStatesService

}
