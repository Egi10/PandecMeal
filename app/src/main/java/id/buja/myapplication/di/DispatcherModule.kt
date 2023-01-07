package id.buja.myapplication.di

import kotlinx.coroutines.Dispatchers
import org.koin.core.qualifier.named
import org.koin.dsl.module

val dispatcherModule = module {
    single(named(MealsDispatchers.IO)) {
        Dispatchers.IO
    }

    single(named(MealsDispatchers.Main)) {
        Dispatchers.Main
    }

    single(named(MealsDispatchers.Default)) {
        Dispatchers.Default
    }
}

object MealsDispatchers {
    const val IO = "io"
    const val Main = "main"
    const val Default = "default"
}