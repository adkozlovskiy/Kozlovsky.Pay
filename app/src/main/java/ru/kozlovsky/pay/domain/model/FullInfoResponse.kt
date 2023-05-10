package ru.kozlovsky.pay.domain.model

import ru.kozlovsky.pay.data.model.dto.CustomerDto

data class FullInfoResponse(
    val customer: CustomerDto,
    val recentRecipients: List<CustomerDto>
)
