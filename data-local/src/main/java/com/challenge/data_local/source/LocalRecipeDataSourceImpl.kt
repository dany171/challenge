package com.challenge.data_local.source

import com.challenge.data_local.db.recipe.RecipeDao
import com.challenge.data_local.db.recipe.RecipeEntity
import com.challenge.data_local.db.recipe.RecipeItemEntity
import com.challenge.data_repository.data_source.local.LocalRecipeDataSource
import com.challenge.domain.entity.Ingredient
import com.challenge.domain.entity.Recipe
import com.challenge.domain.entity.RecipeItem
import com.challenge.domain.entity.UseCaseException
import kotlinx.coroutines.flow.*
import javax.inject.Inject

class LocalRecipeDataSourceImpl @Inject constructor(private val recipeDao: RecipeDao) :
    LocalRecipeDataSource {

    override fun getRecipes(): Flow<List<RecipeItem>> = recipeDao.getRecipes().map { recipes ->
        recipes.map {
            RecipeItem(it.id, it.title)
        }
    }

    override suspend fun addRecipeItems(recipes: List<RecipeItem>) =
        recipeDao.insertRecipes(recipes.map {
            RecipeItemEntity(it.id, it.title)
        })

    override suspend fun addRecipe(recipe: Recipe) {
        recipeDao.insertRecipe(
            RecipeEntity(
                recipe.id,
                recipe.title,
                recipe.summary,
                recipe.ingredients.map { it.name },
                recipe.instructions,
                System.currentTimeMillis()
            )
        )
    }

    override fun getRecipe(id: Long): Flow<Recipe?> = recipeDao.getRecipe(id).map {
        if (it != null) {
            Recipe(
                it.id,
                it.title,
                it.summary,
                it.ingredients.map { Ingredient(it) },
                it.instructions,
                it.updatedAt
            )
        } else {
            null
        }
    }
}
