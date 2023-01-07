package id.buja.myapplication.domain.repository

import id.buja.myapplication.domain.model.Meals
import kotlinx.coroutines.flow.Flow

interface MealsRepository {
    fun getMealsByCategory(category: String): Flow<List<Meals>>
}