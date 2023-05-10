package ru.kozlovsky.pay.domain.usecase

import kotlinx.coroutines.CoroutineDispatcher
import ru.kozlovsky.pay.core.result.failure.Failure
import ru.kozlovsky.pay.core.usecase.AbstractUseCase
import ru.kozlovsky.pay.data.model.dto.SimpleSuccessDto
import ru.kozlovsky.pay.data.repository.authorization.AuthorizationRepository
import ru.kozlovsky.pay.di.IODispatcher
import ru.kozlovsky.pay.domain.model.CheckCodeDto
import javax.inject.Inject

class CheckCodeUseCase @Inject constructor(
    @IODispatcher
    private val ioDispatcher: CoroutineDispatcher,
    private val authorizationRepository: AuthorizationRepository,
) : AbstractUseCase<CheckCodeDto, SimpleSuccessDto, Failure>(ioDispatcher) {
    override suspend fun doInvoke(params: CheckCodeDto): SimpleSuccessDto {
        return authorizationRepository.checkCode(params)
    }
}