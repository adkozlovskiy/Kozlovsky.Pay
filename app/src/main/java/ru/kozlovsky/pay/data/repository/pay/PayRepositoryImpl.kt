package ru.kozlovsky.pay.data.repository.pay

import ru.kozlovsky.pay.data.retrofit.PayService
import javax.inject.Inject

class PayRepositoryImpl @Inject constructor(
    private val payService: PayService,
) : PayRepository {



}