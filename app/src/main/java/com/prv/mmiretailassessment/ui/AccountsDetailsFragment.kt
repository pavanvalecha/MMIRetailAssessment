package com.prv.mmiretailassessment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.prv.mmiretailassessment.R
import com.prv.mmiretailassessment.databinding.FragmentAccountsDetailsBinding
import com.prv.mmiretailassessment.singletons.User
import com.prv.mmiretailassessment.utils.Status
import com.prv.mmiretailassessment.viewmodels.AccountDetailsViewModel
import com.prv.mmiretailassessment.viewmodels.AccountsListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AccountsDetailsFragment : Fragment() {

    val accountsDetailsViewModel: AccountDetailsViewModel by viewModel()

    private var _binding: FragmentAccountsDetailsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    private var accountNoVal: Int = 0

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
        updateValues()
    }

    override fun onResume() {
        super.onResume()
        updateAccountObservers()
    }

    private fun updateValues(){
        val currentBal = accountsDetailsViewModel.getBalance(accountNoVal)
        val overdraft = accountsDetailsViewModel.getOverdraft(accountNoVal)

        binding.textviewBalance.text =  currentBal.toString()
        binding.textviewOverdraft.text =  overdraft.toString()

        binding.buttonDeposit.setOnClickListener {
            val action = AccountsDetailsFragmentDirections.actionAccountsDetailsFragmentToDepositWithdrawFragment(accountNoVal, currentBal)
            action.deposit = true
            Navigation.findNavController(it).navigate(action)
        }
        binding.buttonWithdraw.setOnClickListener {
            val action = AccountsDetailsFragmentDirections.actionAccountsDetailsFragmentToDepositWithdrawFragment(accountNoVal, currentBal)
            action.deposit = false
            Navigation.findNavController(it).navigate(action)
        }
    }

    private fun updateAccountObservers() {
        accountsDetailsViewModel.getAccountUpdates().observe(viewLifecycleOwner, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            Timber.d(resource.toString())
                            if (resource.data != null) {
                                User.UserDetails.accounts = resource.data
                                updateValues()
                            }
                            //resource.data?.let { users -> retrieveList(users) }
                            //binding.progressBar.visibility = View.GONE
                            //binding.listError.visibility = View.GONE
                            //loginSuccess()
                        }
                        Status.ERROR -> {
                            //binding.progressBar.visibility = View.GONE
                            //binding.listError.visibility = View.VISIBLE
                            Timber.d(resource.message)
                        }
                        Status.LOADING -> {
                            //binding.progressBar.visibility = View.VISIBLE
                            //binding.listError.visibility = View.GONE
                        }
                    }
                }
            })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}