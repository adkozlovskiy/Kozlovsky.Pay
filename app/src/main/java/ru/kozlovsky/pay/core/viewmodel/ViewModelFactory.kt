package ru.kozlovsky.pay.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject
import javax.inject.Provider

class ViewModelFactory<V : ViewModel> @Inject constructor(
    private val viewModelProvider: Provider<V>
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <V : ViewModel> create(clazz: Class<V>): V = viewModelProvider.get() as V
}