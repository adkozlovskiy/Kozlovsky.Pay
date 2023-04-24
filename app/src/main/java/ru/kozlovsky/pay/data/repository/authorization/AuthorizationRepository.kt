package ru.kozlovsky.pay.data.repository.authorization

import ru.kozlovsky.pay.domain.model.Profile

interface AuthorizationRepository {
    fun authorize(): Profile
}