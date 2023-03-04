package com.challenge.presentation_recipe.list

import com.challenge.presentation_common.state.UiSingleEvent

sealed class RecipeListUiSingleEvent : UiSingleEvent {

    data class OpenRecipeScreen(val navRoute: String) : RecipeListUiSingleEvent()
}