package ru.kozlovsky.pay.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import ru.kozlovsky.pay.core.result.failure.Failure
import ru.kozlovsky.pay.core.usecase.AbstractUseCase
import ru.kozlovsky.pay.data.model.dto.CustomerDto
import ru.kozlovsky.pay.data.repository.authorization.AuthorizationRepository
import ru.kozlovsky.pay.di.IODispatcher
import ru.kozlovsky.pay.domain.Session
import ru.kozlovsky.pay.domain.model.Credentials
import javax.inject.Inject

class AuthorizationUseCase @Inject constructor(
    @IODispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val authorizationRepository: AuthorizationRepository,
    private val session: Session,
) : AbstractUseCase<Credentials, CustomerDto, Failure>(ioDispatcher) {
    override suspend fun doInvoke(params: Credentials): CustomerDto {
        return authorizationRepository.authorize(params).also {
            session.storeAccessToken(it.id.toString())
        }
    }
}