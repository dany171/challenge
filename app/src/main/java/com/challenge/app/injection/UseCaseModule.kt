package com.challenge.app.injection

import com.challenge.domain.repository.RecipeRepository
import com.challenge.domain.repository.SearchRepository
import com.challenge.domain.usecase.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
class UseCaseModule {

    @Provides
    fun provideUseCaseConfiguration(): UseCase.Configuration = UseCase.Configuration(Dispatchers.IO)

    @Provides
    fun provideGetRecipesUseCase(
        configuration: UseCase.Configuration,
        recipeRepository: RecipeRepository,
        searchRepository: SearchRepository
    ): GetRecipesUseCase = GetRecipesUseCase(
        configuration,
        recipeRepository,
        searchRepository
    )

    @Provides
    fun provideGetRecipeUseCase(
        configuration: UseCase.Configuration,
        recipeRepository: RecipeRepository
    ): GetRecipeUseCase = GetRecipeUseCase(
        configuration,
        recipeRepository
    )
}