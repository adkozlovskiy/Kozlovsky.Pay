package ru.kozlovsky.pay.data.repository.authorization

import ru.kozlovsky.pay.data.model.dto.CustomerDto
import ru.kozlovsky.pay.data.model.dto.SimpleSuccessDto
import ru.kozlovsky.pay.data.retrofit.AuthorizationService
import ru.kozlovsky.pay.domain.model.CheckCodeDto
import ru.kozlovsky.pay.domain.model.Credentials
import ru.kozlovsky.pay.domain.model.RequestCode
import ru.kozlovsky.pay.domain.model.RequestCodeResponse
import javax.inject.Inject

class AuthorizationRepositoryImpl @Inject constructor(
    private val authorizationService: AuthorizationService,
) : AuthorizationRepository {
    override suspend fun requestCode(requestCode: RequestCode): RequestCodeResponse {
        return authorizationService.requestCode(requestCode)
    }

    override suspend fun checkCode(checkCodeDto: CheckCodeDto): SimpleSuccessDto {
        return authorizationService.checkCode(checkCodeDto)
    }

    override suspend fun register(credentials: Credentials): CustomerDto {
        return authorizationService.register(credentials)
    }

    override suspend fun authorize(credentials: Credentials): CustomerDto {
        return authorizationService.authorize(credentials)
    }
}