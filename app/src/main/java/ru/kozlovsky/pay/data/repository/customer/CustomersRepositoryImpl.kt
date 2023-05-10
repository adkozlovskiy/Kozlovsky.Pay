package ru.kozlovsky.pay.data.repository.customer

import ru.kozlovsky.pay.data.retrofit.CustomersService
import ru.kozlovsky.pay.domain.model.FullInfoResponse
import javax.inject.Inject

class CustomersRepositoryImpl @Inject constructor(
    private val customersService: CustomersService,
) : CustomersRepository {
    override suspend fun getFullCustomerInfo(): FullInfoResponse {
        return customersService.getFullCustomerInfo()
    }
}