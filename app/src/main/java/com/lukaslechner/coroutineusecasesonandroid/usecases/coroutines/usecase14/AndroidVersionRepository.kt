package com.lukaslechner.coroutineusecasesonandroid.usecases.coroutines.usecase14

import com.lukaslechner.coroutineusecasesonandroid.mock.AndroidVersion
import com.lukaslechner.coroutineusecasesonandroid.mock.MockApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import timber.log.Timber

class AndroidVersionRepository(
    private var database: AndroidVersionDao,
    private val scope: CoroutineScope,
    private val api: MockApi = mockApi()
) {

    suspend fun getLocalAndroidVersions(): List<AndroidVersion> {
        return database.getAndroidVersions().mapToUiModelList()
    }

    suspend fun loadAndStoreRemoteAndroidVersions(): List<AndroidVersion> {
        return scope.async {
            val recentVersions = api.getRecentAndroidVersions()
            Timber.d("Recent android versions loaded")

            for (version in recentVersions) {
                Timber.d("Insert $version into database")
                database.insert(version.mapToEntity())
            }

            recentVersions
        }.await()
    }

    fun clearDatabase() {
        scope.launch {
            database.clear()
        }
    }
}