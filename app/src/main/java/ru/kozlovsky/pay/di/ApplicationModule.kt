package ru.kozlovsky.pay.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.kozlovsky.pay.data.repository.pay.PayRepository
import ru.kozlovsky.pay.data.repository.pay.PayRepositoryImpl
import ru.kozlovsky.pay.data.retrofit.AuthorizationService
import ru.kozlovsky.pay.data.retrofit.PayService
import javax.inject.Singleton

@Module(
    includes = [
        ApplicationBindingsModule::class
    ]
)
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun providesRetrofitClient(): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(
                GsonConverterFactory.create()
            )
            .build()
    }

    @Provides
    @Singleton
    fun providesPayService(client: Retrofit): PayService {
        return client.create()
    }

    @Provides
    @Singleton
    fun providesAuthorizationService(client: Retrofit): AuthorizationService {
        return client.create()
    }
}

@Module
@InstallIn(SingletonComponent::class)
interface ApplicationBindingsModule {

    @Binds
    @Singleton
    fun bindsPayRepository(impl: PayRepositoryImpl): PayRepository

}