package ru.kozlovsky.pay.data.retrofit

import retrofit2.http.GET
import ru.kozlovsky.pay.domain.model.FullInfoResponse

interface CustomersService {
    @GET("customers/full")
    suspend fun getFullCustomerInfo(): FullInfoResponse
}