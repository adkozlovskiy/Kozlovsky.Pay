package ru.kozlovsky.pay.presentation.scanner.recipient

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import ru.kozlovsky.pay.R
import ru.kozlovsky.pay.databinding.DialogQrRecipientBinding

class RecipientDialogFragment : BottomSheetDialogFragment() {

    private var _binding: DialogQrRecipientBinding? = null
    val binding get() = _binding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = DialogQrRecipientBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvRecipient.text = "здарова петушара"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun getTheme(): Int {
        return R.style.RoundedBottomSheetDialog
    }
}