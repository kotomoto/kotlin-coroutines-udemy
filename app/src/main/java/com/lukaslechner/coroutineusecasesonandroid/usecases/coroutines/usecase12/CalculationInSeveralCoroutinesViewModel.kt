package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase12

import androidx.lifecycle.viewModelScope
import com.lukaslechner.coroutineusecasesonandroid.base.BaseViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.math.BigInteger
import kotlin.system.measureTimeMillis

class CalculationInSeveralCoroutinesViewModel(
    private val factorialCalculator: FactorialCalculator = FactorialCalculator(),
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
) : BaseViewModel<UiState>() {

    fun performCalculation(
        factorialOf: Int,
        numberOfCoroutines: Int
    ) {
        uiState.value = UiState.Loading

        viewModelScope.launch {
            var factorialResult = BigInteger.ZERO
            var computationDuration: Long = 0L
            computationDuration = measureTimeMillis {
                factorialResult =
                    factorialCalculator.calculateFactorial(
                        factorialOf,
                        numberOfCoroutines,
                    )
            }

            var resultString = ""
            var stringConversionDuration = 0L
            stringConversionDuration = measureTimeMillis {
                resultString = convertToString(factorialResult)
            }

            uiState.value =
                UiState.Success(resultString, computationDuration, stringConversionDuration)
        }
    }

    // TODO: execute on background thread
    private suspend fun convertToString(number: BigInteger): String =
        withContext(Dispatchers.Default) {
            number.toString()
        }
}