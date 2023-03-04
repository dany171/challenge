package com.challenge.data_local.db.recipe

import androidx.room.TypeConverter
import com.challenge.domain.entity.Ingredient
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.lang.reflect.Type


class IngredientListTypeConverter {
    private val moshi = Moshi.Builder()
        .add(KotlinJsonAdapterFactory())
        .build()

    private val listType: Type = Types.newParameterizedType(
        List::class.java,
        String::class.java
    )
    private val adapter: JsonAdapter<List<String>> = moshi.adapter(listType)

    @TypeConverter
    fun fromJson(json: String): List<String> {
        return adapter.fromJson(json)?: emptyList()
    }

    @TypeConverter
    fun toJson(ingredients: List<String>): String {
        return adapter.toJson(ingredients)
    }
}