package com.challenge.data_local.source

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.challenge.data_repository.data_source.local.LocalSearchDataSource
import com.challenge.domain.entity.Search
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

internal val KEY_LAST_SEARCH = stringPreferencesKey("key_last_search")

class LocalSearchDataSourceImpl (
    private val dataStore: DataStore<Preferences>
) : LocalSearchDataSource {
    override fun getLastSearch(): Flow<Search> {
        return dataStore.data.map {
            Search(it[KEY_LAST_SEARCH] ?: "")
        }
    }

    override suspend fun saveSearch(search: Search) {
        dataStore.edit {
            it[KEY_LAST_SEARCH] = search.text
        }
    }
}