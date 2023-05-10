package ru.kozlovsky.pay.data.retrofit

import retrofit2.http.Body
import retrofit2.http.POST
import ru.kozlovsky.pay.data.model.dto.CustomerDto
import ru.kozlovsky.pay.data.model.dto.SimpleSuccessDto
import ru.kozlovsky.pay.domain.model.CheckCodeDto
import ru.kozlovsky.pay.domain.model.Credentials
import ru.kozlovsky.pay.domain.model.RequestCode
import ru.kozlovsky.pay.domain.model.RequestCodeResponse

interface AuthorizationService {

    @POST("authorization/request_code")
    suspend fun requestCode(@Body requestCode: RequestCode): RequestCodeResponse

    @POST("authorization/check_code")
    suspend fun checkCode(@Body checkCodeDto: CheckCodeDto): SimpleSuccessDto

    @POST("authorization/register")
    suspend fun register(@Body credentials: Credentials): CustomerDto

    @POST("authorization/auth")
    suspend fun authorize(@Body credentials: Credentials): CustomerDto

}