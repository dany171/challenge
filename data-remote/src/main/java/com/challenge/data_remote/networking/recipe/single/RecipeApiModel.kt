package com.challenge.data_remote.networking.recipe.single

import com.squareup.moshi.Json

data class RecipeApiModel(
    @Json(name = "id") val id: Long,
    @Json(name = "title") val title: String,
    @Json(name = "summary") val summary: String,
    @Json(name = "extendedIngredients") val ingredients: List<IngredientApiModel>,
    @Json(name = "instructions") val instructions: String,
)
data class IngredientApiModel(
    @Json(name = "name") val name: String,
)