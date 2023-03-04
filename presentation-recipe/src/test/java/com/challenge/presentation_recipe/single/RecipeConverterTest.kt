package com.challenge.presentation_recipe.single

import android.content.Context
import com.challenge.domain.entity.Ingredient
import com.challenge.domain.entity.Recipe
import com.challenge.domain.usecase.GetRecipeUseCase
import com.challenge.presentation_recipe.R
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class RecipeConverterTest {

    private val context = mock<Context>()
    private val converter = RecipeConverter(context)

    @Test
    fun testConvertSuccess() {
        val title = "title"
        val summary = "summary"
        val ingredients = listOf(Ingredient("salt"), Ingredient("sugar"))
        val instructions = "Do this"

        val response = GetRecipeUseCase.Response(
            recipe = Recipe(
                id = 1L,
                title = title,
                summary = summary,
                ingredients = ingredients,
                instructions = instructions
            )
        )
        val formattedTitle = "formattedTitle"
        val ingredientsModelList = ingredients.map { IngredientModel(it.name) }
        whenever(context.getString(R.string.title, title)).thenReturn(formattedTitle)
        val result = converter.convertSuccess(response)
        assertEquals(RecipeModel(formattedTitle, summary, ingredientsModelList, instructions), result)
    }
}