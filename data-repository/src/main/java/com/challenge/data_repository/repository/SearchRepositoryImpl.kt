package com.challenge.data_repository.repository

import com.challenge.data_repository.data_source.local.LocalSearchDataSource
import com.challenge.domain.entity.Search
import com.challenge.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.flow

class SearchRepositoryImpl(
    private val searchDataSource: LocalSearchDataSource
): SearchRepository {
    override fun getLastSearch(): Flow<Search> = searchDataSource.getLastSearch()

    override fun saveSearch(search: Search): Flow<Search> = flow {
        searchDataSource.saveSearch(search)
        this.emit(Unit)
    }.flatMapLatest {
        getLastSearch()
    }
}