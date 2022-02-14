package com.example.personalexpensetracker.ui.view.dialog

import android.os.Bundle
import android.view.*
import androidx.navigation.fragment.navArgs
import com.example.personalexpensetracker.databinding.ErrorDialogLayoutBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

class ErrorDialog : BottomSheetDialogFragment() {
    private var _binding: ErrorDialogLayoutBinding? = null
    private val binding get() = _binding!!
    private val args: ErrorDialogArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        _binding = ErrorDialogLayoutBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.run {
            dialogTitle.text = args.title
            dialogMessage.text = args.message
            dialogButtonOk.setOnClickListener {
                dialog?.dismiss()
            }
        }
    }

    override fun onStart() {
        super.onStart()
        dialog?.window?.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}