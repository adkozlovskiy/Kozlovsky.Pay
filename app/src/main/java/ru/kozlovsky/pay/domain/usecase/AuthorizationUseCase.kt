package ru.kozlovsky.pay.domain.usecase

import ru.kozlovsky.pay.data.repository.authorization.AuthorizationRepository
import javax.inject.Inject

class AuthorizationUseCase @Inject constructor(
    private val authorizationRepository: AuthorizationRepository,
) {

    

}