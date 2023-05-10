package ru.kozlovsky.pay.data.repository.customer

import ru.kozlovsky.pay.domain.model.FullInfoResponse

interface CustomersRepository {
    suspend fun getFullCustomerInfo(): FullInfoResponse
}