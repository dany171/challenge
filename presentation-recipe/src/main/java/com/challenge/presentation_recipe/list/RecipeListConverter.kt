package com.challenge.presentation_recipe.list

import android.content.Context
import com.challenge.domain.usecase.GetRecipesUseCase
import com.challenge.presentation_common.state.CommonResultConverter
import com.challenge.presentation_recipe.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RecipeListConverter @Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<GetRecipesUseCase.Response, RecipeListModel>() {

    override fun convertSuccess(data: GetRecipesUseCase.Response): RecipeListModel {
        return RecipeListModel(
            searchText= data.search.text,
            items = data.recipes.map {
                RecipeListItemModel(
                    it.id,
                    context.getString(R.string.title, it.title)
                )
            },
        )
    }
}