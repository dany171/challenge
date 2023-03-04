package com.challenge.data_repository.data_source.local

import com.challenge.domain.entity.Recipe
import com.challenge.domain.entity.RecipeItem
import kotlinx.coroutines.flow.Flow

interface LocalRecipeDataSource {

    fun getRecipes(): Flow<List<RecipeItem>>

    suspend fun addRecipeItems(recipes: List<RecipeItem>)
    suspend fun addRecipe(recipe: Recipe)
    fun getRecipe(id: Long): Flow<Recipe?>
}