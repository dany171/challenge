package com.challenge.data_repository.repository

import com.challenge.data_repository.data_source.local.LocalRecipeDataSource
import com.challenge.data_repository.data_source.remote.RemoteRecipeDataSource
import com.challenge.domain.entity.Recipe
import com.challenge.domain.entity.RecipeItem
import com.challenge.domain.entity.Search
import com.challenge.domain.repository.RecipeRepository
import kotlinx.coroutines.flow.*

class RecipeRepositoryImpl(
    private val remoteRecipeDataSource: RemoteRecipeDataSource,
    private val localRecipeDataSource: LocalRecipeDataSource
) : RecipeRepository {

    override fun getRecipes(search: Search): Flow<List<RecipeItem>> =
        remoteRecipeDataSource.getRecipes(search)
            .onEach {
                localRecipeDataSource.addRecipeItems(it)
            }.map {
                it.ifEmpty {
                    localRecipeDataSource.getRecipes().first()
                }
            }.catch {
                emit(localRecipeDataSource.getRecipes().first())
            }

    override fun getRecipe(id: Long): Flow<Recipe> = localRecipeDataSource.getRecipe(id).map {
        val shouldGetFromRemote =
            it == null || (System.currentTimeMillis() - it.updatedAt) > 604800 //7 days

        if (shouldGetFromRemote) {
            getRecipeFromRemote(id).first()
        } else {
            it!!
        }
    }

    private fun getRecipeFromRemote(id: Long) = remoteRecipeDataSource.getRecipe(id).map {
        localRecipeDataSource.addRecipe(it)
        it
    }

}
