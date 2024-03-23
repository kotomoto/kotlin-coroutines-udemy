package com.lukaslechner.coroutineusecasesonandroid.playground.flow.intermediate_operators

import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.takeWhile

suspend fun main() {

    flowOf(1, 2, 3, 4, 5)
        .takeWhile { it < 3 }
//        .take(3)
        .collect {
            println("$it")
        }
}