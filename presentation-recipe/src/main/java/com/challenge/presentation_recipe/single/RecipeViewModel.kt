package com.challenge.presentation_recipe.single

import androidx.lifecycle.viewModelScope
import com.challenge.domain.usecase.GetRecipeUseCase
import com.challenge.presentation_common.state.MviViewModel
import com.challenge.presentation_common.state.UiSingleEvent
import com.challenge.presentation_common.state.UiState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val recipeUseCase: GetRecipeUseCase,
    private val recipeConverter: RecipeConverter
) : MviViewModel<RecipeModel, UiState<RecipeModel>, RecipeUiAction, UiSingleEvent>() {


    override fun initState(): UiState<RecipeModel> = UiState.Loading

    override fun handleAction(action: RecipeUiAction) {
        when (action) {
            is RecipeUiAction.Load -> {
                loadRecipe(action.recipeId)
            }
        }
    }

    private fun loadRecipe(recipeId: Long) {
        viewModelScope.launch {
            recipeUseCase.execute(GetRecipeUseCase.Request(recipeId))
                .map {
                    recipeConverter.convert(it)
                }
                .collect {
                    submitState(it)
                }
        }
    }
}