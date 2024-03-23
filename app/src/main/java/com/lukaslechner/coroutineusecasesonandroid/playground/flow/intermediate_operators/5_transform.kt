package com.lukaslechner.coroutineusecasesonandroid.playground.flow.intermediate_operators

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.transform

suspend fun main() {

    flowOf(1, 2, 3, 4, 5)
        // possible emission of more elements than in .map()
        .transform {
            emit(it)
            emit(it * 10)
        }
        .collect {
            println("$it")
        }
}