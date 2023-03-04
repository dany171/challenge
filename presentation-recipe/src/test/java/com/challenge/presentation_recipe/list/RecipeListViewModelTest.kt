package com.challenge.presentation_recipe.list

import com.challenge.domain.entity.Result
import com.challenge.domain.usecase.GetRecipesUseCase
import com.challenge.presentation_common.navigation.NavRoutes
import com.challenge.presentation_common.navigation.RecipeInput
import com.challenge.presentation_common.state.UiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
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
import org.mockito.kotlin.verify
import org.mockito.kotlin.whenever

class RecipeListViewModelTest {

    @ExperimentalCoroutinesApi
    private val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()
    private val getRecipesUseCase =
        mock<GetRecipesUseCase>()
    private val converter = mock<RecipeListConverter>()
    private lateinit var viewModel: RecipeListViewModel

    @ExperimentalCoroutinesApi
    @Before
    fun setUp() = runBlockingTest {
        Dispatchers.setMain(testDispatcher)
        viewModel = RecipeListViewModel(
            getRecipesUseCase,
            converter
        )
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
        val uiState = mock<UiState<RecipeListModel>>()
        val result = mock<Result<GetRecipesUseCase.Response>>()
        whenever(
            getRecipesUseCase.execute(
                GetRecipesUseCase.Request("egg")
            )
        ).thenReturn(
            flowOf(
                result
            )
        )
        whenever(converter.convert(result)).thenReturn(uiState)
        viewModel.handleAction(RecipeListUiAction.Load("egg"))
        assertEquals(uiState, viewModel.uiStateFlow.value)
    }


}