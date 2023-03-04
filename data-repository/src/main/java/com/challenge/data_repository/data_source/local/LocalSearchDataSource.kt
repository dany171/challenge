package com.challenge.data_repository.data_source.local

import com.challenge.domain.entity.Search
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

interface LocalSearchDataSource {
    fun getLastSearch(): Flow<Search>
    suspend fun saveSearch(search: Search)
}