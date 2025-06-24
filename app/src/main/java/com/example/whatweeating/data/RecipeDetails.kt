package com.example.whatweeating.data

data class RecipeDetails(
    val id: Int,
    val title: String,
    val image: String?,
    val imageType: String?,
    val servings: Int,
    val readyInMinutes: Int,
    val cookingMinutes: Int?,
    val preparationMinutes: Int?,
    val license: String?,
    val sourceName: String?,
    val sourceUrl: String?,
    val spoonacularSourceUrl: String?,
    val healthScore: Double?,
    val spoonacularScore: Double?,
    val pricePerServing: Double?,
    val analyzedInstructions: List<AnalyzedInstruction>,
    val cheap: Boolean,
    val creditsText: String?,
    val cuisines: List<String>,
    val dairyFree: Boolean,
    val diets: List<String>,
    val gaps: String?,
    val glutenFree: Boolean,
    val instructions: String?,
    val ketogenic: Boolean,
    val lowFodmap: Boolean,
    val occasions: List<String>,
    val sustainable: Boolean,
    val vegan: Boolean,
    val vegetarian: Boolean,
    val veryHealthy: Boolean,
    val veryPopular: Boolean,
    val whole30: Boolean,
    val weightWatcherSmartPoints: Int?,
    val dishTypes: List<String>,
    val extendedIngredients: List<ExtendedIngredient>,
    val summary: String?,
    val winePairing: WinePairing?
)

data class ExtendedIngredient(
    val aisle: String?,
    val amount: Double,
    val consistency: String?,
    val id: Int,
    val image: String?,
    val measures: Measures,
    val meta: List<String>,
    val name: String,
    val original: String,
    val originalName: String,
    val unit: String?
)

data class Measures(
    val metric: MeasureUnit,
    val us: MeasureUnit
)

data class MeasureUnit(
    val amount: Double,
    val unitLong: String?,
    val unitShort: String?
)

data class AnalyzedInstruction(
    val name: String,
    val steps: List<InstructionStep>
)

data class InstructionStep(
    val number: Int,
    val step: String
)

data class WinePairing(
    val pairedWines: List<String>,
    val pairingText: String?,
    val productMatches: List<ProductMatch>
)

data class ProductMatch(
    val id: Int,
    val title: String,
    val description: String?,
    val price: String?,
    val imageUrl: String?,
    val averageRating: Double?,
    val ratingCount: Double?,
    val score: Double?,
    val link: String?
)
