package ru.kozlovsky.pay.presentation.scanner

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import ru.kozlovsky.pay.core.BaseViewModel
import javax.inject.Inject

class ScannerViewModel @Inject constructor() : BaseViewModel() {

    private val _disclaimerVisible = MutableStateFlow(false)
    val disclaimerVisible: StateFlow<Boolean> = _disclaimerVisible.asStateFlow()

    fun setDisclaimerVisible(visible: Boolean) {
        _disclaimerVisible.value = visible
    }

    fun sendQRCode() {

    }
}