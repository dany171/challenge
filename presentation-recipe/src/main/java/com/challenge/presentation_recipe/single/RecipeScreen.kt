package com.challenge.presentation_recipe.single

import android.widget.TextView
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Tab
import androidx.compose.material.TabRow
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.text.HtmlCompat
import com.challenge.presentation_common.navigation.RecipeInput
import com.challenge.presentation_common.state.CommonScreen

@Composable
fun RecipeScreen(
    viewModel: RecipeViewModel,
    recipeInput: RecipeInput
) {
    viewModel.uiStateFlow.collectAsState().value.let { result ->
        CommonScreen(result) { recipeModel ->
//            Recipe(recipeModel)
            RecipeScreen(recipeModel)
        }
    }
    LaunchedEffect(recipeInput.recipeId) {
        viewModel.submitAction(RecipeUiAction.Load(recipeInput.recipeId))
    }
}

@Composable
fun Recipe(recipeModel: RecipeModel) {
    Column(modifier = Modifier.padding(16.dp)) {
        Text(text = recipeModel.title)
        Text(text = recipeModel.summary)
    }
}

@Composable
fun RecipeScreen(recipeModel: RecipeModel) {

    val tabs = listOf("Summary", "ingredients", "Instructions")
    var selectedTabIndex by remember { mutableStateOf(0) }

    Column {
        TabRow(selectedTabIndex) {
            tabs.forEachIndexed { index, title ->
                Tab(
                    selected = selectedTabIndex == index,
                    onClick = { selectedTabIndex = index },
                    modifier = Modifier.padding(16.dp)

                ) {
                    Text(text = title)
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        when (selectedTabIndex) {
            0 -> {
                Summary(summary = recipeModel.summary)
            }
            1 -> {
                Ingredients(recipeModel.ingredients)
            }
            2 -> {
                Instructions(instructions = recipeModel.instructions)
            }
        }
    }
}

@Composable
fun Ingredients(ingredients: List<IngredientModel>) {
    Column(modifier = Modifier.padding(16.dp)) {
        ingredients.forEachIndexed { index, value ->
            Text(text = "${index + 1}. ${value.name}")
        }
    }
}

@Composable
fun Summary(summary: String) {
    HtmlText(
        summary, modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun Instructions(instructions: String) {
    HtmlText(
        instructions, modifier = Modifier.padding(16.dp)
    )
}

@Composable
fun HtmlText(html: String, modifier: Modifier = Modifier) {
    AndroidView(
        modifier = modifier,
        factory = { context -> TextView(context) },
        update = { it.text = HtmlCompat.fromHtml(html, HtmlCompat.FROM_HTML_MODE_COMPACT) }
    )
}