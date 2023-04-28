package ru.kozlovsky.pay.presentation.qr

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import com.google.zxing.BarcodeFormat
import com.google.zxing.qrcode.QRCodeWriter
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import ru.kozlovsky.pay.core.BaseViewModel
import ru.kozlovsky.pay.domain.model.Account
import ru.kozlovsky.pay.presentation.detail.AccountDetailsFragment
import ru.kozlovsky.pay.util.Constants.ACCOUNT_NUMBER_MASK
import ru.kozlovsky.pay.util.ZXingQrCodeGenerator
import ru.kozlovsky.pay.util.extension.getMaskedText
import ru.kozlovsky.pay.util.extension.getParcelableCompat
import javax.inject.Inject

class QrViewModel @Inject constructor(
    private val zXingQrCodeGenerator: ZXingQrCodeGenerator,
) : BaseViewModel() {

    private val _account = MutableStateFlow<Account?>(null)
    val account: Flow<Account> = _account.asStateFlow().filterNotNull()

    val qrCode: Flow<Bitmap?> = account.map {
        zXingQrCodeGenerator.generate(
            it.formattedNumber
        )
    }.filterNotNull()

    override fun reInit(args: Bundle?) {
        super.reInit(args)
        _account.value = args?.getParcelableCompat(AccountDetailsFragment.KEY_ACCOUNT)
    }
}