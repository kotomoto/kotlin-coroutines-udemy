package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase2

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.lukaslechner.coroutineusecasesonandroid.mock.mockVersionFeaturesAndroid10
import com.lukaslechner.coroutineusecasesonandroid.utils.ReplaceMainDispatcherRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import org.junit.rules.TestRule

@OptIn(ExperimentalCoroutinesApi::class)
class Perform2SequentialNetworkRequestsViewModelTest {

    @get:Rule
    val testInstantTaskExecutorRule: TestRule = InstantTaskExecutorRule()

    @get:Rule
    val mainDispatcherRule = ReplaceMainDispatcherRule()

    private val receivedUiStates = mutableListOf<UiState>()

    @Test
    fun `should return Success when both network requests are successful`() = runTest {
        val fakeApi = FakeSuccessApi()
        val viewModel = Perform2SequentialNetworkRequestsViewModel(fakeApi)
        observeViewModel(viewModel)

        viewModel.perform2SequentialNetworkRequest()

        assertEquals(
            listOf(UiState.Loading, UiState.Success(mockVersionFeaturesAndroid10)),
            receivedUiStates,
        )
    }

    private fun observeViewModel(viewModel: Perform2SequentialNetworkRequestsViewModel) {
        viewModel.uiState().observeForever { uiState ->
            if (uiState != null) {
                receivedUiStates.add(uiState)
            }
        }
    }
}