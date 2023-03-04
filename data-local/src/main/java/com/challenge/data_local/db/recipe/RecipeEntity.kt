package com.challenge.data_local.db.recipe

import androidx.room.*

@Entity(tableName = "recipe")
data class RecipeEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "title") val title: String,
    @ColumnInfo(name = "summary") val summary: String,
    @TypeConverters(IngredientListTypeConverter::class)
    @ColumnInfo(name = "ingredients") val ingredients: List<String>,
    @ColumnInfo(name = "instructions") val instructions: String,
    @ColumnInfo(name = "updated_at") var updatedAt: Long = 0,
)
@Entity(tableName = "recipe_item")
data class RecipeItemEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long,
    @ColumnInfo(name = "title") val title: String,
)