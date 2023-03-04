package com.challenge.data_local.injection

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.room.Room
import com.challenge.data_local.db.AppDatabase
import com.challenge.data_local.db.recipe.RecipeDao
import com.challenge.data_local.source.LocalSearchDataSourceImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "my_preferences")

@Module
@InstallIn(SingletonComponent::class)
class PersistenceModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java, "my-database"
        ).build()

    @Provides
    fun provideRecipeDao(appDatabase: AppDatabase): RecipeDao = appDatabase.recipeDao()

    @Provides
    fun provideLocalSearchDataSourceImpl(@ApplicationContext context: Context) =
        LocalSearchDataSourceImpl(context.dataStore)
}