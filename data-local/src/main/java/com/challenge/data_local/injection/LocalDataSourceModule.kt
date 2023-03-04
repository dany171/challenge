package com.challenge.data_local.injection

import com.challenge.data_local.source.LocalRecipeDataSourceImpl
import com.challenge.data_local.source.LocalSearchDataSourceImpl
import com.challenge.data_repository.data_source.local.LocalRecipeDataSource
import com.challenge.data_repository.data_source.local.LocalSearchDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class LocalDataSourceModule {

    @Binds
    abstract fun bindRecipeDataSource(lostDataSourceImpl: LocalRecipeDataSourceImpl): LocalRecipeDataSource

    @Binds
    abstract fun bindSearchDataSource(localSearchDataSourceImpl: LocalSearchDataSourceImpl): LocalSearchDataSource

}