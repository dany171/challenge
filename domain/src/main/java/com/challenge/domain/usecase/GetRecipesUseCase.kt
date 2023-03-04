package com.challenge.domain.usecase

import com.challenge.domain.entity.RecipeItem
import com.challenge.domain.entity.Search
import com.challenge.domain.repository.RecipeRepository
import com.challenge.domain.repository.SearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine

class GetRecipesUseCase(
    configuration: Configuration,
    private val recipeRepository: RecipeRepository,
    private val searchRepository: SearchRepository
) : UseCase<GetRecipesUseCase.Request,
        GetRecipesUseCase.Response>(configuration) {

    override fun process(request: Request): Flow<Response> {
        val newSearch = Search(request.searchText)
        return combine(
            recipeRepository.getRecipes(newSearch),
            searchRepository.saveSearch(newSearch)
        ){ recipes, search ->
            Response(search, recipes)
        }
    }


    data class Request(val searchText: String) : UseCase.Request

    data class Response(
        val search: Search,
        val recipes: List<RecipeItem>
    ) : UseCase.Response
}