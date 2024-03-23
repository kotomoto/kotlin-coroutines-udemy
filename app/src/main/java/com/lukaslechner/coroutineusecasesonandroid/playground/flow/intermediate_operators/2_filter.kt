package com.lukaslechner.coroutineusecasesonandroid.playground.flow.intermediate_operators

import kotlinx.coroutines.flow.filterIsInstance
import kotlinx.coroutines.flow.flowOf

suspend fun main() {

    flowOf(1, 2, 3, 4.0, 5)
        .filterIsInstance<Int>()
//        .filterNotNull()
//        .filterNot { it > 3 }
//        .filter { it > 3 }
        .collect {
            println("$it")
        }
}