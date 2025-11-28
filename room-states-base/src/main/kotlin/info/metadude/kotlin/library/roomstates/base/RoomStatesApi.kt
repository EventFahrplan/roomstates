package info.metadude.kotlin.library.roomstates.base

import okhttp3.Call
import okhttp3.OkHttpClient

interface RoomStatesApi {

    fun provideRoomStatesService(
        baseUrl: String,
        callFactory: Call.Factory = OkHttpClient.Builder().build(),
    ): RoomStatesService

}
