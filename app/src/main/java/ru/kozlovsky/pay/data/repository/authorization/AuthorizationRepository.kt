package ru.kozlovsky.pay.data.repository.authorization

import ru.kozlovsky.pay.data.model.dto.CustomerDto
import ru.kozlovsky.pay.data.model.dto.SimpleSuccessDto
import ru.kozlovsky.pay.domain.model.CheckCodeDto
import ru.kozlovsky.pay.domain.model.Credentials
import ru.kozlovsky.pay.domain.model.RequestCode
import ru.kozlovsky.pay.domain.model.RequestCodeResponse

interface AuthorizationRepository {
    suspend fun requestCode(requestCode: RequestCode): RequestCodeResponse

    suspend fun checkCode(checkCodeDto: CheckCodeDto): SimpleSuccessDto

    suspend fun register(credentials: Credentials): CustomerDto

    suspend fun authorize(credentials: Credentials): CustomerDto
}