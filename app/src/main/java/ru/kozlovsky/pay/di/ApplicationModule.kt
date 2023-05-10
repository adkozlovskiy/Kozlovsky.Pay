package ru.kozlovsky.pay.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import ru.kozlovsky.pay.data.interceptor.TokenInterceptor
import ru.kozlovsky.pay.data.repository.authorization.AuthorizationRepository
import ru.kozlovsky.pay.data.repository.authorization.AuthorizationRepositoryImpl
import ru.kozlovsky.pay.data.repository.customer.CustomersRepository
import ru.kozlovsky.pay.data.repository.customer.CustomersRepositoryImpl
import ru.kozlovsky.pay.data.repository.pay.PayRepository
import ru.kozlovsky.pay.data.repository.pay.PayRepositoryImpl
import ru.kozlovsky.pay.data.retrofit.AuthorizationService
import ru.kozlovsky.pay.data.retrofit.CustomersService
import ru.kozlovsky.pay.data.retrofit.PayService
import javax.inject.Singleton

@Module(
    includes = [
        ApplicationBindingsModule::class,
        CoroutinesDispatchersModule::class,
    ]
)
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    @Singleton
    fun providesOkHttpClient(
        tokenInterceptor: TokenInterceptor,
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(tokenInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofitClient(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
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

    @Provides
    @Singleton
    fun providesCustomersService(client: Retrofit): CustomersService {
        return client.create()
    }

    private const val BASE_URL = "https://skypay.up.railway.app/api/"

}

@Module
@InstallIn(SingletonComponent::class)
interface ApplicationBindingsModule {

    @Binds
    @Singleton
    fun bindsPayRepository(impl: PayRepositoryImpl): PayRepository

    @Binds
    @Singleton
    fun bindsAuthorizationRepository(impl: AuthorizationRepositoryImpl): AuthorizationRepository

    @Binds
    @Singleton
    fun bindsCustomersRepository(impl: CustomersRepositoryImpl): CustomersRepository

}