package ru.kozlovsky.pay.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object CoroutinesDispatchersModule {

    @Provides
    @IODispatcher
    fun providesIODispatcher() = Dispatchers.IO

    @Provides
    @MainDispatcher
    fun providesMainDispatcher() = Dispatchers.Main

}