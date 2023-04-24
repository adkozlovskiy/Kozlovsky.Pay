package ru.kozlovsky.pay.data.repository.pay

import ru.kozlovsky.pay.data.retrofit.PayService
import ru.kozlovsky.pay.data.room.ProfileDao
import javax.inject.Inject

class PayRepositoryImpl @Inject constructor(
    private val payService: PayService,
    private val dao: ProfileDao,
) : PayRepository {


}