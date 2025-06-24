package com.example.whatweeating.network

import com.example.whatweeating.data.RecipeDetails
import com.example.whatweeating.data.RecipeSearchResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface SpoonacularApiService {

    @GET("recipes/complexSearch")
    suspend fun searchRecipes(
        @Query("query") query: String,
        @Query("apiKey") apiKey: String,
        @Query("number") number: Int
    ): RecipeSearchResponse

    @GET("recipes/{id}/information")
    suspend fun getRecipeDetails(
        @Path("id") id: Int,
        @Query("apiKey") apiKey: String
    ): RecipeDetails

    @GET("recipes/complexSearch")
    suspend fun searchByCategory(
        @Query("cuisine") query: String,
        @Query("apiKey") apiKey: String,
        @Query("number") number: Int
    ): RecipeSearchResponse

}