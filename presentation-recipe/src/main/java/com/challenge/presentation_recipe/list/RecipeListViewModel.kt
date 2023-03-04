package com.challenge.presentation_recipe.list

import androidx.lifecycle.viewModelScope
import com.challenge.domain.usecase.GetRecipesUseCase
import com.challenge.presentation_common.navigation.NavRoutes
import com.challenge.presentation_common.navigation.RecipeInput
import com.challenge.presentation_common.state.MviViewModel
import com.challenge.presentation_common.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeListViewModel @Inject constructor(
    private val useCase: GetRecipesUseCase,
    private val converter: RecipeListConverter,
) : MviViewModel<RecipeListModel, UiState<RecipeListModel>, RecipeListUiAction, RecipeListUiSingleEvent>() {

    override fun initState(): UiState<RecipeListModel> = UiState.Idle

    override fun handleAction(action: RecipeListUiAction) {
        when (action) {
            is RecipeListUiAction.Load -> {
                loadRecipes(action.searchText)
            }
            is RecipeListUiAction.RecipeClick -> {
                submitSingleEvent(
                    RecipeListUiSingleEvent.OpenRecipeScreen(
                        NavRoutes.Recipe.routeForRecipe(
                            RecipeInput(action.recipeId)
                        )
                    )
                )
            }
        }

    }

    private fun loadRecipes(searchText: String) {
        viewModelScope.launch {
            useCase.execute(GetRecipesUseCase.Request(searchText))
                .map {
                    converter.convert(it)
                }
                .collect {
                    submitState(it)
                }
        }

    }

}