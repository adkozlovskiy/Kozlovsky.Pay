package ru.kozlovsky.pay.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import ru.kozlovsky.pay.core.viewmodel.ViewModelFactory
import ru.kozlovsky.pay.domain.navigation.NavigationEvent
import ru.kozlovsky.pay.util.extension.collectOnLifecycle
import javax.inject.Inject

abstract class BaseFragment<V : BaseViewModel, B : ViewBinding> : Fragment() {

    abstract val viewModelClass: Class<V>

    @Inject
    lateinit var viewModelFactory: ViewModelFactory<V>

    val viewModel by lazy { getViewModelInstance() }

    protected open fun getViewModelInstance(): V {
        return ViewModelProvider(this as Fragment, viewModelFactory)
            .get(viewModelClass)
    }

    private var _binding: B? = null
    protected val binding: B
        get() = _binding ?: error("Trying to access the binding outside of the view lifecycle.")

    abstract fun getViewBinding(): B

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = getViewBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.reInit(arguments)
        configureView()
        observeViewModel()
    }

    open fun configureView() {}

    open fun observeViewModel() {
        // Подписываемся на события навигации
        collectOnLifecycle(viewModel.navigationEvent) {
            proceedNavigation(it)
        }
    }

    override fun onDestroyView() {
        arguments?.clear()
        super.onDestroyView()
    }

    private fun proceedNavigation(event: NavigationEvent) {
        val navController = findNavController()
        when (event) {
            is NavigationEvent.Up -> {
                navController.navigateUp()
            }
            is NavigationEvent.Navigation -> {
                navController.navigate(event.targetRes, event.args, event.navOptions)
            }
        }
    }
}