# Room states changelog

## NEXT

* Not published yet.

### Changes

* Fix `SimpleRoomStatesRepository#callFactory` property name.


## [v.1.0.1](https://github.com/EventFahrplan/roomstates/releases/tag/v.1.0.1)

* Published: 2025-02-07

### Changes

* Use kotlin:2.1.10.
* Fix bug where the room state cannot be retrieved. This affects an Android app which uses obfuscation via ProGuard/R8.
  * Error: `IllegalArgumentException: Unable to create call adapter for retrofit2.Call<kotlin.Result>`
  * Related: https://github.com/skydoves/retrofit-adapters/issues/24


## [v.1.0.0](https://github.com/EventFahrplan/roomstates/releases/tag/v.1.0.0)

* Published: 2025-01-14

### Changes

* This is the initial release. Have fun!
