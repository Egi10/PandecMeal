package id.buja.myapplication.data.repository.mapping

import id.buja.myapplication.data.source.response.Meal
import id.buja.myapplication.domain.model.Meals

fun List<Meal>?.mappingToUseCaseEntity(): List<Meals> {
    val newList: MutableList<Meals> = mutableListOf()

    this?.forEach {
        newList.add(
            Meals(
                id = it.idMeal,
                name = it.strMeal,
                image = it.strMealThumb
            )
        )
    }



    return if (this.isNullOrEmpty()) {
        emptyList()
    } else {
        newList
    }
}