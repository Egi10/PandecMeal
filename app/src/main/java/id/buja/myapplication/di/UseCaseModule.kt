package id.buja.myapplication.di

import id.buja.myapplication.domain.usecase.GetMealsByCategoryUseCase
import org.koin.dsl.module

val useCaseModule = module {
    factory {
        GetMealsByCategoryUseCase(get())
    }
}