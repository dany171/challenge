package com.challenge.presentation_recipe.single

import android.content.Context
import com.challenge.domain.usecase.GetRecipeUseCase
import com.challenge.presentation_common.state.CommonResultConverter
import com.challenge.presentation_recipe.R
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject

class RecipeConverter @Inject constructor(@ApplicationContext private val context: Context) :
    CommonResultConverter<GetRecipeUseCase.Response, RecipeModel>() {

    override fun convertSuccess(data: GetRecipeUseCase.Response): RecipeModel {
        return RecipeModel(
            context.getString(R.string.title, data.recipe.title),
            data.recipe.summary,
            data.recipe.ingredients.map {
                IngredientModel(it.name)
            },
            data.recipe.instructions
        )
    }
}