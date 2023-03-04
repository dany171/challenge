package com.challenge.data_remote.source

import com.challenge.data_remote.networking.recipe.list.GetRecipesApiModel
import com.challenge.data_remote.networking.recipe.RecipeItemApiModel
import com.challenge.data_remote.networking.recipe.RecipeService
import com.challenge.data_remote.networking.recipe.single.IngredientApiModel
import com.challenge.data_remote.networking.recipe.single.RecipeApiModel
import com.challenge.domain.entity.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class RemoteRecipeDataSourceImplTest {

    private val recipeService = mock<RecipeService>()
    private val recipeDataSource = RemoteRecipeDataSourceImpl(recipeService)

    @ExperimentalCoroutinesApi
    @Test
    fun testGetRecipes() = runBlockingTest {
        val searchText = "egg"
        val search = Search(searchText)
        val remoteRecipes = listOf(RecipeItemApiModel(1, "title"))
        val getRecipesApiModel = GetRecipesApiModel(remoteRecipes)
        val expectedRecipes = listOf(RecipeItem(1, "title"))
        whenever(recipeService.getRecipes(searchText)).thenReturn(getRecipesApiModel)
        val result = recipeDataSource.getRecipes(search).first()
        Assert.assertEquals(expectedRecipes, result)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetRecipe() = runBlockingTest {
        val id = 1L
        val title = "title"
        val summary = "summary"
        val ingredientsApiModel = listOf<IngredientApiModel>()
        val ingredients = listOf<Ingredient>()
        val instructions = "instructions"

        val remoteRecipe = RecipeApiModel(id, title, summary, ingredientsApiModel, instructions)
        val expectedRecipe = Recipe(id, title, summary, ingredients, instructions)

        whenever(recipeService.getRecipe(id)).thenReturn(remoteRecipe)
        val result = recipeDataSource.getRecipe(id).first()
        Assert.assertEquals(expectedRecipe, result)
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetRecipesThrowsError() = runBlockingTest {
        val searchText = "egg"
        val search = Search(searchText)
        whenever(recipeService.getRecipes(searchText)).thenThrow(RuntimeException())
        recipeDataSource.getRecipes(search).catch {
            Assert.assertTrue(it is UseCaseException.RecipeException)
        }.collect()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testGetRecipeThrowsError() = runBlockingTest {
        val id = 1L
        whenever(recipeService.getRecipe(id)).thenThrow(RuntimeException())
        recipeDataSource.getRecipe(id).catch {
            Assert.assertTrue(it is UseCaseException.RecipeException)
        }.collect()
    }
}