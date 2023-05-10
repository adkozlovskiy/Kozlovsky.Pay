package ru.kozlovsky.pay.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import ru.kozlovsky.pay.core.result.failure.Failure
import ru.kozlovsky.pay.core.usecase.AbstractUseCase
import ru.kozlovsky.pay.data.repository.authorization.AuthorizationRepository
import ru.kozlovsky.pay.di.IODispatcher
import ru.kozlovsky.pay.domain.model.RequestCode
import ru.kozlovsky.pay.domain.model.RequestCodeResponse
import javax.inject.Inject

class RequestCodeUseCase @Inject constructor(
    @IODispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val authorizationRepository: AuthorizationRepository,
) : AbstractUseCase<RequestCode, RequestCodeResponse, Failure>(ioDispatcher) {
    override suspend fun doInvoke(params: RequestCode): RequestCodeResponse {
        return authorizationRepository.requestCode(params)
    }
}