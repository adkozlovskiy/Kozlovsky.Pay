package ru.kozlovsky.pay.presentation.home

import android.os.Bundle
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import ru.kozlovsky.pay.core.BaseViewModel
import ru.kozlovsky.pay.data.model.dto.CustomerDto
import javax.inject.Inject

class HomeViewModel @Inject constructor(

) : BaseViewModel() {

    private val _recentRecipients = MutableStateFlow(emptyList<CustomerDto>())
    val recentRecipients: StateFlow<List<CustomerDto>> = _recentRecipients.asStateFlow()

    override fun reInit(args: Bundle?) {
        super.reInit(args)
    }

    fun onShake() = viewModelScope.launch {

    }
}