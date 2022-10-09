# Polygon JVM Client SDK written in Kotlin

A client SDK for Polygon.io's API.

Supports Polygon's [REST](https://polygon.io/docs/#getting-started)
and [WebSocket](https://polygon.io/sockets) APIs

### Wisp Capital Disclaimer

This fork is custom to my own needs, and is not intended to be used by anyone else.

A few of the changes include

* Latest Ktor
* Latest Kotlin
* Removed support for some amount of client custom engine - because I won't use it.

I will likely add and remove functionality at will - for whatever suits my own work :).

## Features

Everything you'd expect from a client SDK plus...

- Configurable HTTP client
  via [HttpClientProvider](src/main/kotlin/io/polygon/kotlin/sdk/HttpClientProvider.kt)
- Asynchronous APIs built on top of Kotlin co-routines
- Idiomatic interoperability with Java
  - Synchronous and callback based APIs
  - Generated builder classes for API parameter data classes

