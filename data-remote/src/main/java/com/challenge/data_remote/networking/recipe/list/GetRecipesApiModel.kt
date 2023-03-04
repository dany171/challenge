package com.challenge.data_remote.networking.recipe.list

import com.challenge.data_remote.networking.recipe.RecipeItemApiModel
import com.squareup.moshi.Json

data class GetRecipesApiModel(
    @Json(name = "results") val results: List<RecipeItemApiModel>
)
