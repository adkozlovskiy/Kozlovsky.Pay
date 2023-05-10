package ru.kozlovsky.pay.presentation.pin

import androidx.core.content.res.ResourcesCompat
import dagger.hilt.android.AndroidEntryPoint
import ru.kozlovsky.pay.R
import ru.kozlovsky.pay.core.BaseFragment
import ru.kozlovsky.pay.databinding.FragmentPinCodeBinding
import ru.kozlovsky.pay.util.extension.collectOnLifecycle

@AndroidEntryPoint
class PinCodeFragment : BaseFragment<PinCodeViewModel, FragmentPinCodeBinding>() {
    override val viewModelClass: Class<PinCodeViewModel>
        get() = PinCodeViewModel::class.java

    override fun getViewBinding(): FragmentPinCodeBinding {
        return FragmentPinCodeBinding.inflate(layoutInflater)
    }

    private val selectedColor by lazy {
        ResourcesCompat.getColor(
            resources, R.color.blue_accent, requireActivity().theme
        )
    }

    private val unselectedColor by lazy {
        ResourcesCompat.getColor(
            resources, R.color.background_deep, requireActivity().theme
        )
    }

    override fun configureView() {
        super.configureView()
        with(binding) {
            configureDigits()
            action.setOnClickListener {
                viewModel.actionButtonClicked()
            }
            logout.setOnClickListener {
                viewModel.logout()
            }
        }
    }

    private fun configureDigits() {
        with(binding) {
            digitOne.value.text = "1"
            digitOne.card.setOnClickListener {
                viewModel.addDigit("1")
            }

            digitTwo.value.text = "2"
            digitTwo.card.setOnClickListener {
                viewModel.addDigit("2")
            }

            digitThree.value.text = "3"
            digitThree.card.setOnClickListener {
                viewModel.addDigit("3")
            }

            digitFour.value.text = "4"
            digitFour.card.setOnClickListener {
                viewModel.addDigit("4")
            }

            digitFive.value.text = "5"
            digitFive.card.setOnClickListener {
                viewModel.addDigit("5")
            }

            digitSix.value.text = "6"
            digitSix.card.setOnClickListener {
                viewModel.addDigit("6")
            }

            digitSeven.value.text = "7"
            digitSeven.card.setOnClickListener {
                viewModel.addDigit("7")
            }

            digitEight.value.text = "8"
            digitEight.card.setOnClickListener {
                viewModel.addDigit("8")
            }

            digitNine.value.text = "9"
            digitNine.card.setOnClickListener {
                viewModel.addDigit("9")
            }

            digitZero.value.text = "0"
            digitZero.card.setOnClickListener {
                viewModel.addDigit("0")
            }
        }
    }

    override fun observeViewModel() {
        super.observeViewModel()
        collectOnLifecycle(viewModel.pinCode) {
            with(binding) {
                dotOne.setCardBackgroundColor(
                    if (it.isNotEmpty()) selectedColor else unselectedColor
                )
                dotTwo.setCardBackgroundColor(
                    if (it.length > 1) selectedColor else unselectedColor
                )
                dotThree.setCardBackgroundColor(
                    if (it.length > 2) selectedColor else unselectedColor
                )
                dotFour.setCardBackgroundColor(
                    if (it.length > 3) selectedColor else unselectedColor
                )

                if (it.isEmpty()) {
                    icon.setImageResource(R.drawable.ic_fingerprint)
                } else {
                    icon.setImageResource(R.drawable.ic_backspace)
                }
            }
        }
    }
}