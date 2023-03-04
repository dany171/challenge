package com.challenge.domain.repository

import com.challenge.domain.entity.Search
import kotlinx.coroutines.flow.Flow

interface SearchRepository {
    fun getLastSearch(): Flow<Search>
    fun saveSearch(search: Search): Flow<Search>
}