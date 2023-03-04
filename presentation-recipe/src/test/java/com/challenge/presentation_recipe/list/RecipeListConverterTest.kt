package com.challenge.presentation_recipe.list

import android.content.Context
import com.challenge.domain.entity.Recipe
import com.challenge.domain.entity.RecipeItem
import com.challenge.domain.entity.Search
import com.challenge.domain.usecase.GetRecipesUseCase
import com.challenge.presentation_recipe.R
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class RecipeListConverterTest {

    private val context = mock<Context>()
    private val converter = RecipeListConverter(context)

    @Test
    fun testConvertSuccess() {
        val response = GetRecipesUseCase.Response(
            Search("egg"),
            recipes = listOf(
                RecipeItem(
                    id = 1L,
                    title = "Splash egg"
                )
            )
        )
        val formattedRecipeTitle = "formattedRecipeTitle"
        whenever(context.getString(R.string.title, "Splash egg")).thenReturn(formattedRecipeTitle)
        val result = converter.convertSuccess(response)
        assertEquals(
            RecipeListModel(
                searchText = "egg",
                items = listOf(
                    RecipeListItemModel(
                        id = 1L,
                        title = formattedRecipeTitle
                    )
                )
            ), result
        )
    }
}