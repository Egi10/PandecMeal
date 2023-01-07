package id.buja.myapplication.data.source

import id.buja.myapplication.data.source.response.MealsResponse
import id.buja.myapplication.data.source.service.MealsService

class MealsDataSourceImpl(
    private val mealsService: MealsService
): MealsDataSource {
    override suspend fun getMealsByCategory(category: String): MealsResponse {
        return mealsService.getMealsByCategory(
            category = category
        )
    }
}