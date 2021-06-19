package com.prv.mmiretailassessment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.prv.mmiretailassessment.R
import com.prv.mmiretailassessment.databinding.FragmentDepositWithdrawBinding
import com.prv.mmiretailassessment.viewmodels.AccountsListViewModel
import com.prv.mmiretailassessment.viewmodels.DepositWithdrawViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class DepositWithdrawFragment : Fragment() {

    val depositWithdrawViewModel: DepositWithdrawViewModel by viewModel()

    private var _binding: FragmentDepositWithdrawBinding? = null
    private val binding get() = _binding!!
    private var depositMode = false
    val args: DepositWithdrawFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDepositWithdrawBinding.inflate(inflater, container, false)
        depositMode = args.deposit
        setupUI()
        return binding.root
    }

    private fun setupUI(){
        if (depositMode) {
            binding.buttonDeposit.setText(R.string.deposit_text)
        } else {
            binding.buttonDeposit.setText(R.string.withdraw_text)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonDeposit.setOnClickListener {
            //depositWithdrawViewModel.depositAmount()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}