package com.challenge.domain.usecase

import com.challenge.domain.entity.Recipe
import com.challenge.domain.repository.RecipeRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever


class GetRecipeUseCaseTest {

    private val recipeRepository = mock<RecipeRepository>()
    private val useCase = GetRecipeUseCase(
        mock(),
        recipeRepository
    )

    @ExperimentalCoroutinesApi
    @Test
    fun testProcess() = runBlockingTest {
        val request = GetRecipeUseCase.Request(0L)
        val recipe = Recipe(1L, "title", "summary", listOf(), "ingredients")
        whenever(recipeRepository.getRecipe(request.recipeId)).thenReturn(flowOf(recipe))
        val response = useCase.process(request).first()
        assertEquals(GetRecipeUseCase.Response(recipe), response)
    }
}