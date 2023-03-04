package com.challenge.data_local.source

import com.challenge.data_local.db.recipe.RecipeDao
import com.challenge.data_local.db.recipe.RecipeItemEntity
import com.challenge.domain.entity.RecipeItem
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class LocalRecipeDataSourceImplTest {

    private val recipeDao = mock<RecipeDao>()
    private val recipeDataSource = LocalRecipeDataSourceImpl(recipeDao)

    @ExperimentalCoroutinesApi
    @Test
    fun testGetRecipes() = runBlockingTest {
        val localRecipes = listOf(RecipeItemEntity(1, "title"))
        val expectedRecipes = listOf(RecipeItem(1, "title"))
        whenever(recipeDao.getRecipes()).thenReturn(flowOf(localRecipes))
        val result = recipeDataSource.getRecipes().first()
        Assert.assertEquals(expectedRecipes, result)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testAddRecipes() = runBlockingTest {
        val localRecipes = listOf(RecipeItemEntity(1, "title"))
        val recipes = listOf(RecipeItem(1, "title"))
        recipeDataSource.addRecipeItems(recipes)
        verify(recipeDao).insertRecipes(localRecipes)
    }

}