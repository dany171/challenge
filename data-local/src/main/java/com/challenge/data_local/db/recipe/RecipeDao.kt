package com.challenge.data_local.db.recipe

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.challenge.domain.entity.Recipe
import kotlinx.coroutines.flow.Flow

@Dao
interface RecipeDao {

    @Query("SELECT * FROM recipe_item")
    fun getRecipes(): Flow<List<RecipeItemEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipes(recipes: List<RecipeItemEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertRecipe(recipe: RecipeEntity)

    @Query("SELECT * FROM recipe WHERE id==:id")
    fun getRecipe(id: Long): Flow<RecipeEntity?>
}