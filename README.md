[![Build](https://github.com/EventFahrplan/roomstates/actions/workflows/build.yaml/badge.svg)](https://github.com/EventFahrplan/roomstates/actions/workflows/build.yaml) [![Maven Central](https://maven-badges.herokuapp.com/maven-central/info.metadude.kotlin.library.roomstates/room-states-repositories/badge.svg)](https://maven-badges.herokuapp.com/maven-central/info.metadude.kotlin.library.roomstates/room-states-repositories) [![Apache License](http://img.shields.io/badge/license-Apache%20License%202.0-lightgrey.svg)](http://choosealicense.com/licenses/apache-2.0/)

# Room states library

A Kotlin library containing a parser and models for the FOSDEM room states API:

* https://api.fosdem.org


## Usage

The library is published as two separate artifacts: `room-states-base` and `room-states-repositories`.
You can use either of them depending on your needs.

### Usage of `room-states-base`

The `room-states-base` artifact returns a `Result` type
from the suspending `RoomStatesService#getRooms` function.

``` kotlin
val api: RoomStatesApi = Api
val service: RoomStatesService = api.provideRoomStatesService(
    baseUrl = "https://api.fosdem.org",
    callFactory = okHttpClient,
)
val rooms: Result<List<Room>> = service.getRooms(
    path = "/roomstatus/v1/listrooms",
)
```

### Usage of `room-states-repositories`

The `room-states-repositories` artifact returns a `Flow<Result>` type
from the suspending `RoomStatesRepository#getRooms` function.

``` kotlin
val repository: RoomStatesRepository = SimpleRoomStatesRepository(
    url = "https://api.fosdem.org",
    path = "/roomstatus/v1/listrooms",
    callFactory = okHttpClient,
    api = Api,
)
val rooms: Flow<Result<List<Room>>> = repository.getRooms()
```


## Gradle build

To deploy the library to your local Maven repository run the following task:

``` bash
$ ./gradlew publishToMavenLocal
```

Then, to use the library in your project add the following to
your top level `build.gradle`:

``` groovy
allprojects {
    repositories {
        mavenLocal()
    }
}
```

and one of the following dependencies to your application module `build.gradle`:


``` groovy
dependencies {
    implementation "info.metadude.kotlin.library.roomstates:room-states-base:$version"
    implementation "info.metadude.kotlin.library.roomstates:room-states-repositories:$version"
}
```


## Tests

Run the following command to execute all tests:

``` bash
$ ./gradlew clean test
```

## Author

* [Tobias Preuss][tobias-preuss]

## License

    Copyright 2025 Tobias Preuss

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.


[tobias-preuss]: https://github.com/johnjohndoe
