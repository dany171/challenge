package com.challenge.presentation_recipe.list

import com.challenge.presentation_common.state.UiAction

sealed class RecipeListUiAction : UiAction {

    data class Load(val searchText: String) : RecipeListUiAction()
    data class RecipeClick(val recipeId: Long) : RecipeListUiAction()
}