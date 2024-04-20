package com.lukaslechner.coroutineusecasesonandroid.playground.flow.concurrency

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlin.system.measureTimeMillis

suspend fun main(): Unit = coroutineScope {

    val flow = MutableStateFlow(0)

    launch {
        flow.collect {
            println("Collector 1 processes $it")
        }
    }

    launch {
        flow.collect {
            println("Collector 2 processes $it")
            delay(100)
        }
    }


    launch {
        val timeToEmit = measureTimeMillis {
            repeat(5) {
                flow.emit(it)
                delay(10)
            }
        }
        println("Time to emit all values: $timeToEmit ms")
    }
}