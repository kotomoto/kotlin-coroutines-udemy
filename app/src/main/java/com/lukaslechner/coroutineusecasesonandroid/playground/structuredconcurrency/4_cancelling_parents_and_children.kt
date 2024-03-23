package com.lukaslechner.coroutineusecasesonandroid.playground.structuredconcurrency

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking<Unit> {
    val scope = CoroutineScope(Dispatchers.Default)

    scope.coroutineContext[Job]!!.invokeOnCompletion {
        if (it is CancellationException) {
            println("Parent job was cancelled")
        }
    }

    val job1 = scope.launch {
        delay(1000)
        println("Coroutine 1 completed")
    }
    job1.invokeOnCompletion {
        if (it is CancellationException) {
            println("Coroutine 1 was cancelled")
        }
    }

    scope.launch {
        delay(1000)
        println("Coroutine 2 completed")
    }.invokeOnCompletion {
        if (it is CancellationException) {
            println("Coroutine 2 was cancelled")
        }
    }

    delay(200)

    job1.cancelAndJoin()

//    scope.cancel()
//    scope.coroutineContext[Job]!!.cancelAndJoin()
}