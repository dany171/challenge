package com.challenge.data_local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.challenge.data_local.db.recipe.IngredientListTypeConverter
import com.challenge.data_local.db.recipe.RecipeDao
import com.challenge.data_local.db.recipe.RecipeEntity
import com.challenge.data_local.db.recipe.RecipeItemEntity

@Database(entities = [RecipeEntity::class, RecipeItemEntity::class], version = 1)
@TypeConverters(IngredientListTypeConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun recipeDao(): RecipeDao
}