package com.challenge.presentation_recipe.single

data class RecipeModel(
    val title: String,
    val summary: String,
    val ingredients: List<IngredientModel>,
    val instructions: String
)

data class IngredientModel(
    val name: String
)