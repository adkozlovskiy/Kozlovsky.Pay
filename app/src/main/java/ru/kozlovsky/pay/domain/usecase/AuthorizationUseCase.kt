package ru.kozlovsky.pay.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import ru.kozlovsky.pay.core.result.failure.Failure
import ru.kozlovsky.pay.core.usecase.AbstractUseCase
import ru.kozlovsky.pay.data.model.dto.CustomerDto
import ru.kozlovsky.pay.data.repository.authorization.AuthorizationRepository
import ru.kozlovsky.pay.di.IODispatcher
import javax.inject.Inject

class AuthorizationUseCase @Inject constructor(
    @IODispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val authorizationRepository: AuthorizationRepository,
) : AbstractUseCase<Unit, CustomerDto, Failure>(ioDispatcher) {
    override suspend fun doInvoke(params: Unit): CustomerDto {
        return authorizationRepository.authorize()
    }
}