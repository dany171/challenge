package com.challenge.presentation_recipe.list

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.challenge.presentation_common.state.CommonScreen
import com.challenge.presentation_common.state.UiState
import kotlinx.coroutines.flow.collectLatest

@Composable
fun RecipeListScreen(
    viewModel: RecipeListViewModel,
    navController: NavController
) {

    Column(modifier = Modifier.padding(16.dp)) {
        var text by remember {
            mutableStateOf(
                (viewModel.uiStateFlow.value as? UiState.Success)?.data?.searchText ?: ""
            )
        }
        OutlinedTextField(
            value = text,
            onValueChange = {
                text = it
                viewModel.submitAction(RecipeListUiAction.Load(it))

            },
            label = { Text("Enter recipe name") },
            maxLines = 1,
        )

        viewModel.uiStateFlow.collectAsState().value.let { state ->
            CommonScreen(state = state) {
                RecipeList(it) { recipeListItem ->
                    viewModel.submitAction(RecipeListUiAction.RecipeClick(recipeListItem.id))
                }
            }
        }

        LaunchedEffect(Unit) {
            viewModel.singleEventFlow.collectLatest {
                when (it) {
                    is RecipeListUiSingleEvent.OpenRecipeScreen -> {
                        navController.navigate(it.navRoute)
                    }
                }
            }
        }
    }
}


@Composable
fun RecipeList(
    recipeListModel: RecipeListModel,
    onRowClick: (RecipeListItemModel) -> Unit
) {
    LazyColumn(modifier = Modifier.padding(16.dp)) {

        items(recipeListModel.items) { item ->
            Column(modifier = Modifier
                .padding(16.dp)
                .clickable {
                    onRowClick(item)
                }) {
                Text(text = item.title)
            }
        }
    }
}