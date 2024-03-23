package com.lukaslechner.coroutineusecasesonandroid.playground.flow.exceptionhandling

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.retryWhen
import kotlinx.coroutines.launch

suspend fun main(): Unit = coroutineScope {
    launch {
        stocksFlow()
            .catch { throwable ->
                println("Handle exception in catch() operator $throwable")
            }
            .collect { stocksData ->
                println("Collected $stocksData")
            }
    }
}

private fun stocksFlow(): Flow<String> = flow {
    repeat(5) { index ->
        delay(1000)

        if (index < 4) {
            emit("New stock data")
        } else {
            throw NetworkException("Network request failed!")
        }
    }
//}.retry(retries = 2) { cause ->
}.retryWhen { cause, attempt ->
    println("Enter retry() with $cause and attempt: $attempt")
    delay(1000)
    cause is NetworkException
}

class NetworkException(message: String): Exception(message)