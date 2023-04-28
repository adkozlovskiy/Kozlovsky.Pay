package ru.kozlovsky.pay.presentation.data

import dagger.hilt.android.AndroidEntryPoint
import ru.kozlovsky.pay.core.BaseFragment
import ru.kozlovsky.pay.databinding.FragmentCustomerDataBinding
import ru.kozlovsky.pay.domain.navigation.navigateUp

@AndroidEntryPoint
class CustomerDataFragment: BaseFragment<CustomerDataViewModel, FragmentCustomerDataBinding>() {
    override val viewModelClass: Class<CustomerDataViewModel>
        get() = CustomerDataViewModel::class.java

    override fun getViewBinding(): FragmentCustomerDataBinding {
        return FragmentCustomerDataBinding.inflate(layoutInflater)
    }

    override fun configureView() {
        super.configureView()
        with(binding) {
            ftToolbar.setNavigationOnClickListener {
                viewModel.navigateUp()
            }
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
    }
}