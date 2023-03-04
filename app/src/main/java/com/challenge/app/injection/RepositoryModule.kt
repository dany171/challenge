package com.challenge.app.injection

import com.challenge.data_repository.data_source.local.LocalRecipeDataSource
import com.challenge.data_repository.data_source.local.LocalSearchDataSource
import com.challenge.data_repository.data_source.remote.RemoteRecipeDataSource
import com.challenge.data_repository.repository.RecipeRepositoryImpl
import com.challenge.data_repository.repository.SearchRepositoryImpl
import com.challenge.domain.repository.RecipeRepository
import com.challenge.domain.repository.SearchRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    fun provideRecipeRepository(
        remoteRecipeDataSource: RemoteRecipeDataSource,
        localRecipeDataSource: LocalRecipeDataSource
    ): RecipeRepository = RecipeRepositoryImpl(
        remoteRecipeDataSource,
        localRecipeDataSource
    )

    @Provides
    fun provideSearchRepository(
        searchDataSource: LocalSearchDataSource
    ): SearchRepository = SearchRepositoryImpl(searchDataSource)
}