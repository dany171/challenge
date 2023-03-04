package com.challenge.data_remote.networking.recipe

import com.squareup.moshi.Json

data class RecipeItemApiModel(
    @Json(name = "id") val id: Long,
    @Json(name = "title") val title: String,
)

