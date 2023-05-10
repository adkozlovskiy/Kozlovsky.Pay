package ru.kozlovsky.pay.core

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import ru.kozlovsky.pay.R
import ru.kozlovsky.pay.core.viewmodel.ViewModelFactory
import ru.kozlovsky.pay.domain.navigation.NavigationEvent
import javax.inject.Inject

abstract class BaseBottomSheetDialog<V : BaseViewModel, B : ViewBinding> : BottomSheetDialogFragment() {

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
        configureView()
        observeViewModel()
        val reInit = arguments?.getBoolean(KEY_RE_INIT)
        if (reInit != false) {
            viewModel.reInit(arguments)
        }
    }

    open fun configureView() {
        findNavController().currentBackStackEntry?.savedStateHandle?.getLiveData<Any?>(KEY_RESULT)
            ?.observe(viewLifecycleOwner) { result ->
                viewModel.onResult(result)
            }
    }

    open fun observeViewModel() {
        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.navigationEvent.collect {
                proceedNavigation(it)
            }
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
                if (event.popBackStack != null) {
                    navController.popBackStack(event.popBackStack, true)
                }
                val arguments = event.args ?: bundleOf()
                arguments.putBoolean(KEY_RE_INIT, true)
                navController.navigate(event.targetRes, arguments, event.navOptions)
            }

            is NavigationEvent.Result -> {
                proceedResult(event.value)
            }
        }
    }

    private fun proceedResult(value: Any?) {
        findNavController().previousBackStackEntry?.savedStateHandle?.set(KEY_RESULT, value)
    }

    override fun getTheme(): Int {
        return R.style.RoundedBottomSheetDialog
    }

    companion object {
        const val KEY_RE_INIT = "reInit"
        const val KEY_RESULT = "result"
    }
}