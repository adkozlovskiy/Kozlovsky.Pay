package ru.kozlovsky.pay.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import ru.kozlovsky.pay.core.result.failure.Failure
import ru.kozlovsky.pay.core.usecase.AbstractUseCase
import ru.kozlovsky.pay.data.repository.customer.CustomersRepository
import ru.kozlovsky.pay.di.IODispatcher
import ru.kozlovsky.pay.domain.model.FullInfoResponse
import javax.inject.Inject

class GetFullCustomerInfoUseCase @Inject constructor(
    @IODispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val customersRepository: CustomersRepository,
) : AbstractUseCase<Unit, FullInfoResponse, Failure>(ioDispatcher) {
    override suspend fun doInvoke(params: Unit): FullInfoResponse {
        return customersRepository.getFullCustomerInfo()
    }
}