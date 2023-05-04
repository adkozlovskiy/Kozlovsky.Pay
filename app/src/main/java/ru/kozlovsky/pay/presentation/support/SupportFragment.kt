package ru.kozlovsky.pay.presentation.support

import dagger.hilt.android.AndroidEntryPoint
import ru.kozlovsky.pay.R
import ru.kozlovsky.pay.core.BaseFragment
import ru.kozlovsky.pay.databinding.FragmentSupportBinding
import ru.kozlovsky.pay.domain.navigation.navigateUp
import ru.kozlovsky.pay.presentation.adapter.MarginItemDecoration
import ru.kozlovsky.pay.presentation.adapter.compositeAdapter
import ru.kozlovsky.pay.util.extension.StringConstants
import ru.kozlovsky.pay.util.extension.collectOnLifecycle
import ru.kozlovsky.pay.util.extension.showKeyboard

@AndroidEntryPoint
class SupportFragment : BaseFragment<SupportViewModel, FragmentSupportBinding>() {
    override val viewModelClass: Class<SupportViewModel>
        get() = SupportViewModel::class.java

    override fun getViewBinding(): FragmentSupportBinding {
        return FragmentSupportBinding.inflate(layoutInflater)
    }

    private val adapter = compositeAdapter {
        add(
            SupportIncomingMessageDelegate()
        )
        add(
            SupportOutcomingMessageDelegate()
        )
    }

    override fun configureView() {
        super.configureView()
        with(binding) {
            ftToolbar.setNavigationOnClickListener {
                viewModel.navigateUp()
            }
            showKeyboard(input.isiInput)
            messages.adapter = adapter
            messages.addItemDecoration(
                MarginItemDecoration(R.dimen.padding8)
            )
            input.icSend.setOnClickListener {
                val content = input.isiInput.text.toString()
                viewModel.sendMessage(content)
                input.isiInput.setText(StringConstants.EMPTY_STRING)
            }
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
        collectOnLifecycle(viewModel.messages) {
            adapter.submitList(it)
            binding.messages.smoothScrollToPosition(it.lastIndex)
        }
    }
}