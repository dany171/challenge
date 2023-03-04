package com.challenge.presentation_recipe.single

import com.challenge.domain.entity.Result
import com.challenge.domain.usecase.GetRecipeUseCase
import com.challenge.presentation_common.state.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.kotlin.mock
import org.mockito.kotlin.whenever

class RecipeViewModelTest {

    @ExperimentalCoroutinesApi
    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
    private val useCase = mock<GetRecipeUseCase>()
    private val converter = mock<RecipeConverter>()
    private lateinit var viewModel: RecipeViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() = runBlockingTest {
        Dispatchers.setMain(testDispatcher)
        viewModel = RecipeViewModel(useCase, converter)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @ExperimentalCoroutinesApi
    @Test
    fun testHandleActionLoad() = runBlockingTest {
        assertEquals(UiState.Loading, viewModel.uiStateFlow.value)
        val recipeId = 1L
        val uiState = mock<UiState<RecipeModel>>()
        val result = mock<Result<GetRecipeUseCase.Response>>()
        whenever(useCase.execute(GetRecipeUseCase.Request(recipeId))).thenReturn(
            flowOf(
                result
            )
        )
        whenever(converter.convert(result)).thenReturn(uiState)
        viewModel.handleAction(RecipeUiAction.Load(recipeId))
        assertEquals(uiState, viewModel.uiStateFlow.value)
    }
}