package com.lukaslechner.coroutineusecasesonandroid.playground.flow.concurrency

import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.mapLatest

suspend fun main() = coroutineScope {

    val flow = flow {
        repeat(5) {
            println("Emitter - emit start $it")
            delay(100)
            println("Emitter - emit finished of $it")
            emit(it)
        }
    }.mapLatest {
        println("Decorate item $it")
        delay(200)
        it
    }

    flow.collect {
        println("Collector - start collecting $it")
        delay(300)
        println("Collector - finished collecting $it")
    }
}