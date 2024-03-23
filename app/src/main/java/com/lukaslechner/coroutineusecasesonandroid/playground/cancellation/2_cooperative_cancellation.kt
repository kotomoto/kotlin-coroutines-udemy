package com.lukaslechner.coroutineusecasesonandroid.playground.cancellation

import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.isActive
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

fun main() = runBlocking {

    val job = launch(Dispatchers.Default) {
        repeat(10) { index ->
//            ensureActive() - 1
//            yield() - 2
            if (isActive) {
                println("operation number $index")
                Thread.sleep(100)
            } else {
//                return@launch
                println("Cleanup ...")
                throw CancellationException()
            }
        }
    }

    delay(250)
    println("Cancelling coroutine")
    job.cancel()

}