package com.challenge.data_repository.repository

import com.challenge.data_repository.data_source.local.LocalRecipeDataSource
import com.challenge.data_repository.data_source.remote.RemoteRecipeDataSource
import com.challenge.domain.entity.Recipe
import com.challenge.domain.entity.RecipeItem
import com.challenge.domain.entity.Search
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


class RecipeRepositoryImplTest {

    private val remoteRecipeDataSource = mock<RemoteRecipeDataSource>()
    private val localRecipeDataSource = mock<LocalRecipeDataSource>()
    private val repositoryImpl = RecipeRepositoryImpl(remoteRecipeDataSource, localRecipeDataSource)


    @ExperimentalCoroutinesApi
    @Test
    fun testGetRecipes() = runBlockingTest {
        val searchText = "egg"
        val search = Search(searchText)
        val recipes = listOf(RecipeItem(1, "title"))
        whenever(remoteRecipeDataSource.getRecipes(search)).thenReturn(flowOf(recipes))
        val result = repositoryImpl.getRecipes(search).first()
        Assert.assertEquals(recipes, result)
        verify(localRecipeDataSource).addRecipeItems(recipes)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetRecipe() = runBlockingTest {
        val id = 1L
        val recipe = Recipe(id, "title", "summary", listOf(), "instructions")
        whenever(remoteRecipeDataSource.getRecipe(id)).thenReturn(flowOf(recipe))
        whenever(localRecipeDataSource.getRecipe(id)).thenReturn(flowOf(recipe))
        val result = repositoryImpl.getRecipe(id).first()
        Assert.assertEquals(recipe, result)
    }
}