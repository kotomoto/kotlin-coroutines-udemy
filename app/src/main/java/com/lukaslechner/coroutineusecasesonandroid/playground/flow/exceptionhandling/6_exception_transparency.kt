package com.lukaslechner.coroutineusecasesonandroid.playground.flow.exceptionhandling

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow

suspend fun main(): Unit = coroutineScope {

    flow {
        emit(1)
    }.catch {
        println("Handled exception in catch operator")
    }
        .collect { emittedValue ->
            throw Exception("Exception in collect(): $emittedValue")
        }
}
