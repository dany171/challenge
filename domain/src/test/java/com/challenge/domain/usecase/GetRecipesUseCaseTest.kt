package com.challenge.domain.usecase

import com.challenge.domain.entity.Recipe
import com.challenge.domain.entity.RecipeItem
import com.challenge.domain.entity.Search
import com.challenge.domain.repository.RecipeRepository
import com.challenge.domain.repository.SearchRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert.assertEquals
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever


class GetRecipesUseCaseTest {

    private val recipeRepository = mock<RecipeRepository>()
    private val searchRepository = mock<SearchRepository>()
    private val useCase = GetRecipesUseCase(
        mock(),
        recipeRepository,
        searchRepository
    )

    @ExperimentalCoroutinesApi
    @Test
    fun testProcess() = runBlockingTest {
        val searchText = "egg"
        val search = Search(searchText)
        val recipe1 = RecipeItem(1L, "title1")
        val recipe2 = RecipeItem(2L, "title2")
        val recipe3 = RecipeItem(3L,  "title3")
        val recipe4 = RecipeItem(4L, "title4")

        whenever(recipeRepository.getRecipes(search)).thenReturn(flowOf(listOf(recipe1, recipe2, recipe3, recipe4)))
        whenever(searchRepository.saveSearch(search)).thenReturn(flowOf(search))

        val response = useCase.process(GetRecipesUseCase.Request(searchText)).first()
        assertEquals(
            GetRecipesUseCase.Response(
                search,
                listOf(
                    recipe1,
                    recipe2,
                    recipe3,
                    recipe4,
                )
            ),
            response
        )
        verify(searchRepository).saveSearch(search)
    }
}