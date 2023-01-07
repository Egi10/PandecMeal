package id.buja.myapplication.data.source.service

import id.buja.myapplication.data.source.response.MealsResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface MealsService {
    @GET("filter.php")
    suspend fun getMealsByCategory(
        @Query("c") category: String
    ): MealsResponse
}