package com.lukaslechner.coroutineusecasesonandroid.playground.fundamentals

import kotlinx.coroutines.runBlocking
import kotlin.concurrent.thread

fun main(): Unit = runBlocking {
    repeat(1_000_000) {
        thread {
            Thread.sleep(5_000)
            print(".")
        }
    }
}