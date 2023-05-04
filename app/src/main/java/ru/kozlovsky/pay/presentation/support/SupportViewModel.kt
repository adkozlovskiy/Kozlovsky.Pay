package ru.kozlovsky.pay.presentation.support

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import ru.kozlovsky.pay.core.BaseViewModel
import ru.kozlovsky.pay.domain.model.Message
import javax.inject.Inject

class SupportViewModel @Inject constructor() : BaseViewModel() {

    private val _messages = MutableStateFlow(emptyList<Message>())
    val messages = _messages.asStateFlow()

    init {
        // todo unmock
        _messages.value = listOf(
            Message(System.currentTimeMillis(), "Привет", true),
            Message(System.currentTimeMillis(), "Привет!", false),
            Message(System.currentTimeMillis(), "Деньги не приходят на мой счет, подскажите, что делать", true),
            Message(System.currentTimeMillis(), "Деньги придут в течение получаса", false)
        )
    }

    fun sendMessage(content: String) {
        _messages.update {
            ArrayList(it).apply {
                add(
                    Message(System.currentTimeMillis(), content, true)
                )
            }
        }
    }
}