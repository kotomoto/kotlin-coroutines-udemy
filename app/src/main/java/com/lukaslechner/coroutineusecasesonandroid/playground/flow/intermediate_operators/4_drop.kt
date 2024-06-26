package com.lukaslechner.coroutineusecasesonandroid.playground.flow.intermediate_operators

import kotlinx.coroutines.flow.dropWhile
import kotlinx.coroutines.flow.flowOf

suspend fun main() {

    flowOf(1, 2, 3, 4, 5)
        .dropWhile { it < 2 }
//        .drop(3)
        .collect {
            println("$it")
        }
}