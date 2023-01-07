package id.buja.myapplication.data.repository

import id.buja.myapplication.data.repository.mapping.mappingToUseCaseEntity
import id.buja.myapplication.data.source.MealsDataSource
import id.buja.myapplication.domain.model.Meals
import id.buja.myapplication.domain.repository.MealsRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

class MealsRepositoryImpl(
    private val mealsDataSource: MealsDataSource,
    private val dispatcher: CoroutineDispatcher,
): MealsRepository {
    override fun getMealsByCategory(category: String): Flow<List<Meals>> {
        return flow {
            emit(
                mealsDataSource.getMealsByCategory(
                    category = category
                ).meals.mappingToUseCaseEntity()
            )
        }.flowOn(dispatcher)
    }
}