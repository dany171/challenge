package com.challenge.presentation_recipe.list

data class RecipeListModel(
    var searchText: String = "",
    val items: List<RecipeListItemModel> = listOf()
)

data class RecipeListItemModel(
    val id: Long,
    val title: String
)