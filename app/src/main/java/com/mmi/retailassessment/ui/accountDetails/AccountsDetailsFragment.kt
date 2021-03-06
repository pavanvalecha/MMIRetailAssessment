package com.mmi.retailassessment.ui.accountDetails

import timber.log.Timber
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.mmi.retailassessment.utils.Status
import com.mmi.retailassessment.singletons.User
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.mmi.retailassessment.viewModels.AccountDetailsViewModel
import com.mmi.retailassessment.databinding.FragmentAccountsDetailsBinding


/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AccountsDetailsFragment : Fragment() {

    private var accountNoVal: Int = 0
    private var _binding: FragmentAccountsDetailsBinding? = null

    private val binding get() = _binding!!
    private val args: AccountsDetailsFragmentArgs by navArgs()
    private val accountsDetailsViewModel: AccountDetailsViewModel by viewModel()

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

    /**
     * @summary fragment method to update the values on UI
     */
    private fun updateValues(){
        val currentBal = accountsDetailsViewModel.getBalance(accountNoVal)
        val overdraft = accountsDetailsViewModel.getOverdraft(accountNoVal)

        binding.textviewBalance.text =  currentBal.toString()
        binding.textviewOverdraft.text =  overdraft.toString()

        binding.buttonDeposit.setOnClickListener {
            val action =
                AccountsDetailsFragmentDirections.actionAccountsDetailsFragmentToDepositWithdrawFragment(
                    accountNoVal,
                    currentBal
                )
            action.deposit = true
            Navigation.findNavController(it).navigate(action)
        }
        binding.buttonWithdraw.setOnClickListener {
            val action =
                AccountsDetailsFragmentDirections.actionAccountsDetailsFragmentToDepositWithdrawFragment(
                    accountNoVal,
                    currentBal
                )
            action.deposit = false
            Navigation.findNavController(it).navigate(action)
        }
    }

    /**
     * @summary method to setup observers for updating UI State as per data changes
     */
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
                        }
                        Status.ERROR -> {
                            Timber.d(resource.message)
                        }
                        Status.LOADING -> {
                            Timber.d("Loading..")
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