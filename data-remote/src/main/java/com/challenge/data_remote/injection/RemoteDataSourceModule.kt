package com.challenge.data_remote.injection

import com.challenge.data_remote.source.RemoteRecipeDataSourceImpl
import com.challenge.data_repository.data_source.remote.RemoteRecipeDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class RemoteDataSourceModule {

    @Binds
    abstract fun bindRecipeDataSource(recipeDataSourceImpl: RemoteRecipeDataSourceImpl): RemoteRecipeDataSource

}