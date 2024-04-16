package com.lukaslechner.coroutineusecasesonandroid.playground.flow.concurrency

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.conflate
import kotlinx.coroutines.flow.flow

suspend fun main() = coroutineScope {

    val flow = flow {
        repeat(5) {
            println("Emitter - emit start $it")
            delay(100)
            println("Emitter - emit finished of $it")
            emit(it)
        }
    }.conflate()

    flow.collect {
        println("Collector - start collecting $it")
        delay(300)
        println("Collector - finished collecting $it")
    }
}