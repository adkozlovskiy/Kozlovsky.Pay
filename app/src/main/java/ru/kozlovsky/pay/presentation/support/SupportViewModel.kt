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
        _messages.value = listOf(
            Message(System.currentTimeMillis(),
                "Здравствуйте, отправил деньги на счет, а они до сих пор не пришли",
                true),
            Message(System.currentTimeMillis(), "Здравствуйте, уточните, пожалуйста, когда делала перевод?", false),
            Message(System.currentTimeMillis(), "Вчера в 18:57", true),
            Message(System.currentTimeMillis(),
                "Перевод задерживается. Пожалуйста, ожидайте ещё около получаса",
                false),
            Message(System.currentTimeMillis(), "Могу я еще чем-то помочь?", false)
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