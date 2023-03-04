package com.challenge.presentation_common.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavType
import androidx.navigation.navArgument

private const val ROUTE_RECIPES = "recipes"
private const val ROUTE_RECIPE = "recipes/%s"
private const val ARG_RECIPE_ID = "recipeId"

sealed class NavRoutes(
    val route: String,
    val arguments: List<NamedNavArgument> = emptyList()
) {

    object Recipes : NavRoutes(ROUTE_RECIPES)

    object Recipe : NavRoutes(
        route = String.format(ROUTE_RECIPE, "{$ARG_RECIPE_ID}"),
        arguments = listOf(navArgument(ARG_RECIPE_ID) {
            type = NavType.LongType
        })
    ) {

        fun routeForRecipe(recipeInput: RecipeInput) = String.format(ROUTE_RECIPE, recipeInput.recipeId)

        fun fromEntry(entry: NavBackStackEntry): RecipeInput {
            return RecipeInput(entry.arguments?.getLong(ARG_RECIPE_ID) ?: 0L)
        }
    }
}