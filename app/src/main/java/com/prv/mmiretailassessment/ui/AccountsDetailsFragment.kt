package com.prv.mmiretailassessment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.prv.mmiretailassessment.R
import com.prv.mmiretailassessment.databinding.FragmentAccountsDetailsBinding
import com.prv.mmiretailassessment.viewmodels.AccountDetailsViewModel
import com.prv.mmiretailassessment.viewmodels.AccountsListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AccountsDetailsFragment : Fragment() {

    val accountsDetailsViewModel: AccountDetailsViewModel by viewModel()

    private var _binding: FragmentAccountsDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private lateinit var accountNoVal: String

    val args: AccountsDetailsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAccountsDetailsBinding.inflate(inflater, container, false)
        accountNoVal = args.accountNo
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.textviewBalance.text =  accountsDetailsViewModel.getBalance(accountNoVal).toString()
        binding.textviewOverdraft.text =  accountsDetailsViewModel.getOverdraft(accountNoVal).toString()

        binding.buttonDeposit.setOnClickListener {
            val action = AccountsDetailsFragmentDirections.actionAccountsDetailsFragmentToDepositWithdrawFragment()
            action.deposit = true
            Navigation.findNavController(view).navigate(action)
        }
        binding.buttonWithdraw.setOnClickListener {
            val action = AccountsDetailsFragmentDirections.actionAccountsDetailsFragmentToDepositWithdrawFragment()
            action.deposit = false
            Navigation.findNavController(view).navigate(action)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}