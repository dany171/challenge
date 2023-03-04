package com.challenge.data_remote.networking.recipe

import com.challenge.data_remote.networking.recipe.list.GetRecipesApiModel
import com.challenge.data_remote.networking.recipe.single.RecipeApiModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RecipeService {

    @GET("/recipes/search")
    suspend fun getRecipes(@Query("query") query: String): GetRecipesApiModel

    @GET("/recipes/{recipeId}/information")
    suspend fun getRecipe(@Path("recipeId") id: Long): RecipeApiModel
}