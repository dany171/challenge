package com.challenge.presentation_recipe.single

import com.challenge.presentation_common.state.UiAction

sealed class RecipeUiAction : UiAction {

    data class Load(val recipeId: Long) : RecipeUiAction()
}