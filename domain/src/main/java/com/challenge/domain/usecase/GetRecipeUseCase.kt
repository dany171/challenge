package com.challenge.domain.usecase

import com.challenge.domain.entity.Recipe
import com.challenge.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class GetRecipeUseCase(
    configuration: Configuration,
    private val recipeRepository: RecipeRepository
) : UseCase<GetRecipeUseCase.Request, GetRecipeUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> =
        recipeRepository.getRecipe(request.recipeId)
            .map {
                Response(it)
            }

    data class Request(val recipeId: Long) : UseCase.Request
    data class Response(val recipe: Recipe) : UseCase.Response
}