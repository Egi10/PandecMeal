package id.buja.myapplication.di

import id.buja.myapplication.data.source.MealsDataSource
import id.buja.myapplication.data.source.MealsDataSourceImpl
import org.koin.dsl.module

val dataSourceModule = module {
    single<MealsDataSource> {
        MealsDataSourceImpl(get())
    }
}