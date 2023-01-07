package id.buja.myapplication.domain.usecase

import id.buja.myapplication.domain.model.Meals
import id.buja.myapplication.domain.repository.MealsRepository
import kotlinx.coroutines.flow.Flow

class GetMealsByCategoryUseCase(
    private val mealsRepository: MealsRepository
) {

    operator fun invoke(category: String): Flow<List<Meals>> {
        return mealsRepository.getMealsByCategory(
            category = category
        )
    }
}