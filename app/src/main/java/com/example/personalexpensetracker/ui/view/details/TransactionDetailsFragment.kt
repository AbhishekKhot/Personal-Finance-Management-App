package com.example.personalexpensetracker.ui.view.details

import android.os.Bundle
import android.view.*
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.personalexpensetracker.R
import com.example.personalexpensetracker.databinding.FragmentTransactionDetailsBinding
import com.example.personalexpensetracker.model.Transaction
import com.example.personalexpensetracker.resources.DetailState
import com.example.personalexpensetracker.ui.view.base.BaseFragment
import com.example.personalexpensetracker.utils.*
import com.example.personalexpensetracker.viewmodel.TransactionViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

@AndroidEntryPoint
class TransactionDetailsFragment :
    BaseFragment<FragmentTransactionDetailsBinding, TransactionViewModel>() {
    override val viewModel: TransactionViewModel by activityViewModels()
    private val args: TransactionDetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getByID(args.transaction.id)

        lifecycleScope.launchWhenCreated {
            viewModel.detailState.collect {
                when (it) {
                    DetailState.Loading -> {}
                    is DetailState.Success -> {
                        onDetailsLoaded(args.transaction)
                    }
                    DetailState.Empty -> {
                        findNavController().navigateUp()
                    }
                }
            }
        }
    }


    private fun onDetailsLoaded(transaction: Transaction) = with(binding.transactionDetails) {
        title.text = transaction.title
        amount.text = indianRupee(transaction.amount).cleanTextContent
        type.text = transaction.transactionType
        tag.text = transaction.date
        date.text = transaction.date
        note.text = transaction.note
        createdAt.text = transaction.createdAtDateFormat

        binding.editTransaction.setOnClickListener {
            val bundle = Bundle().apply {
                putSerializable("transaction", transaction)
            }
            findNavController().navigate(
                R.id.action_transactionDetailsFragment_to_editTransactionFragment,
                bundle
            )
        }
    }

    override fun getViewBinding(
        inflator: LayoutInflater,
        container: ViewGroup?,
    ): FragmentTransactionDetailsBinding {
        return FragmentTransactionDetailsBinding.inflate(inflator, container, false)
    }
}