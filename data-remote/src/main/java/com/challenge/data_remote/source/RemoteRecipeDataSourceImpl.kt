package com.challenge.data_remote.source

import com.challenge.data_remote.networking.recipe.RecipeItemApiModel
import com.challenge.data_remote.networking.recipe.RecipeService
import com.challenge.data_remote.networking.recipe.single.RecipeApiModel
import com.challenge.data_repository.data_source.remote.RemoteRecipeDataSource
import com.challenge.domain.entity.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class RemoteRecipeDataSourceImpl @Inject constructor(private val recipeService: RecipeService) :
    RemoteRecipeDataSource {

    override fun getRecipes(search: Search): Flow<List<RecipeItem>> = flow {
        emit(recipeService.getRecipes(search.text))
    }.map { getRecipesApiModel ->
        getRecipesApiModel.results.map { recipeItemApiModel ->
            convertList(recipeItemApiModel)
        }
    }.catch {
        println(it)
        throw UseCaseException.RecipeException(it)
    }

    override fun getRecipe(id: Long): Flow<Recipe> = flow {
        emit(recipeService.getRecipe(id))
    }.map {
        convertSingle(it)
    }.catch {
        println(it)
        throw UseCaseException.RecipeException(it)
    }

    private fun convertList(recipeItemApiModel: RecipeItemApiModel) =
        RecipeItem(recipeItemApiModel.id, recipeItemApiModel.title)

    private fun convertSingle(recipeApiModel: RecipeApiModel) =
        Recipe(
            recipeApiModel.id,
            recipeApiModel.title,
            recipeApiModel.summary,
            recipeApiModel.ingredients.map { Ingredient(it.name) },
            recipeApiModel.instructions
        )
}