package ru.kozlovsky.pay.presentation.scanner

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.kozlovsky.pay.core.BaseViewModel
import ru.kozlovsky.pay.domain.model.Recipient
import javax.inject.Inject

class ScannerViewModel @Inject constructor() : BaseViewModel() {

    private val _disclaimerVisible = MutableStateFlow(false)
    val disclaimerVisible: StateFlow<Boolean> = _disclaimerVisible.asStateFlow()

    private val _foundAccount = MutableSharedFlow<Recipient>(extraBufferCapacity = 1)
    val foundAccount = _foundAccount.asSharedFlow()

    private var accountFoundInProgress = false

    fun setDisclaimerVisible(visible: Boolean) {
        _disclaimerVisible.value = visible
    }

    override fun onResult(value: Any?) {
        super.onResult(value)
        if (value == true) {
            accountFoundInProgress = false
        }
    }

    fun searchForAccount(raw: String?) {
        if (raw == null || accountFoundInProgress) return

        viewModelScope.launch {
            _foundAccount.emit(
                Recipient("kozlovsky", "350")
            )
            accountFoundInProgress = true
        }
    }
}