package info.metadude.kotlin.library.roomstates.repositories.simple

import info.metadude.kotlin.library.roomstates.base.Api
import okhttp3.OkHttpClient

internal suspend fun main() {
    val repository = SimpleRoomStatesRepository(
        url = "https://gist.githubusercontent.com",
        path = "johnjohndoe/1f96ca8c2cecc44a05f28e28548d545d/raw/4f15259e9d5f2f58c919c408226007801bdef821/rooms.json",
        httpClient = OkHttpClient.Builder().build(),
        api = Api,
    )

    repository.getRooms().collect {
        println("$it")
    }

    println("Done.")
}
