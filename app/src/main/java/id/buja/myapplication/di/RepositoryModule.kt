package id.buja.myapplication.di

import id.buja.myapplication.data.repository.MealsRepositoryImpl
import id.buja.myapplication.domain.repository.MealsRepository
import org.koin.core.qualifier.named
import org.koin.dsl.module

val repositoryModule = module {
    single<MealsRepository> {
        MealsRepositoryImpl(get(),get(named(MealsDispatchers.IO)))
    }
}