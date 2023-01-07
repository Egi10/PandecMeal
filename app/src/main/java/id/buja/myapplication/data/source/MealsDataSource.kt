package id.buja.myapplication.data.source

import id.buja.myapplication.data.source.response.MealsResponse

interface MealsDataSource {
    suspend fun getMealsByCategory(category: String): MealsResponse
}