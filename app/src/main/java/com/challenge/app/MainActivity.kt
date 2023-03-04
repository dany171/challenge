package com.challenge.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.challenge.app.ui.theme.ChallengeAppTheme
import com.challenge.presentation_common.navigation.NavRoutes
import com.challenge.presentation_recipe.list.RecipeListScreen
import com.challenge.presentation_recipe.single.RecipeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChallengeAppTheme {
                Surface(color = MaterialTheme.colors.background) {
                    val navController = rememberNavController()
                    App(navController = navController)
                }
            }
        }
    }
}

@Composable
fun App(navController: NavHostController) {
    NavHost(navController, startDestination = NavRoutes.Recipes.route) {
        composable(route = NavRoutes.Recipes.route) {
            RecipeListScreen(hiltViewModel(), navController)
        }
        composable(
            route = NavRoutes.Recipe.route,
            arguments = NavRoutes.Recipe.arguments
        ) {
            RecipeScreen(
                hiltViewModel(),
                NavRoutes.Recipe.fromEntry(it)
            )
        }
    }
}

