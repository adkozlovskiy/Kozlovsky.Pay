package ru.kozlovsky.pay.data.repository.authorization

import ru.kozlovsky.pay.data.model.dto.CustomerDto

interface AuthorizationRepository {
    fun authorize(): CustomerDto
}