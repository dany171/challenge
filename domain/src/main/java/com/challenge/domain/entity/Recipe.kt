package com.challenge.domain.entity

data class Recipe(
    val id: Long,
    val title: String,
    val summary: String,
    val ingredients: List<Ingredient>,
    val instructions: String,
    val updatedAt: Long = 0
)

data class RecipeItem(
    val id: Long,
    val title: String,
)

data class Ingredient(
    val name: String
)