package com.challenge.domain.repository

import com.challenge.domain.entity.Recipe
import com.challenge.domain.entity.RecipeItem
import com.challenge.domain.entity.Search
import kotlinx.coroutines.flow.Flow

interface RecipeRepository {

    fun getRecipes(search: Search): Flow<List<RecipeItem>>

    fun getRecipe(id: Long): Flow<Recipe>
}