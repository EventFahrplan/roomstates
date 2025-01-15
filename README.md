[![Maven Central](https://maven-badges.herokuapp.com/maven-central/info.metadude.kotlin.library.roomstates/room-states-repositories/badge.svg)](https://maven-badges.herokuapp.com/maven-central/info.metadude.kotlin.library.roomstates/room-states-repositories) [![Apache License](http://img.shields.io/badge/license-Apache%20License%202.0-lightgrey.svg)](http://choosealicense.com/licenses/apache-2.0/)

# Room states library

A Kotlin library containing a parser and models for the FOSDEM room states API:

* https://api.fosdem.org


## Usage

``` kotlin
val repository: RoomStatesRepository = SimpleRoomStatesRepository(
    url = "https://api.fosdem.org",
    path = "/roomstatus/v1/listrooms",
    httpClient = okHttpClient,
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
