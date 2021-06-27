package com.mmi.retailassessment.ui.depositWithdraw

import android.os.Bundle
import timber.log.Timber
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import com.mmi.retailassessment.R
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.mmi.retailassessment.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.mmi.retailassessment.viewModels.DepositWithdrawViewModel
import com.mmi.retailassessment.databinding.FragmentDepositWithdrawBinding


class DepositWithdrawFragment : Fragment() {

    private val depositWithdrawViewModel: DepositWithdrawViewModel by viewModel()

    private var _binding: FragmentDepositWithdrawBinding? = null
    private val binding get() = _binding!!
    val args: DepositWithdrawFragmentArgs by navArgs()

    private var fromListPage = false
    private var depositMode = false
    private var currentBal: Float = 0.0F
    private var accNo: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDepositWithdrawBinding.inflate(inflater, container, false)
        fromListPage = args.fromListPage
        depositMode = args.deposit
        currentBal = args.currentBal
        accNo = args.accNo
        setupUI()
        return binding.root
    }

    /**
     * @summary class method to setup UI depending on deposit or withdraw mode
     */
    private fun setupUI() {
        if (depositMode) {
            binding.buttonDeposit.setText(R.string.deposit_text)
        } else {
            binding.buttonDeposit.setText(R.string.withdraw_text)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonDeposit.setOnClickListener {
            updateBalance()
        }
    }

    /**
     * @summary method to set values to amount Edit text and create observers depending on deposit or withdraw mode
     */
    private fun updateBalance() {
        val changeAmt = binding.amountEdittext.text.toString().toFloat()
        if (depositMode) {
            depositObservers(accNo, changeAmt, currentBal)
        } else {
            withdrawObservers(accNo, changeAmt, currentBal)
        }
    }

    /**
     * @summary method to setup observers for deposit action updating UI State as per data changes
     * @param Int - account Number, Float - difference amount, Float - current balance
     */
    private fun depositObservers(accNo: Int, changeAmt: Float, currentBal: Float) {
        depositWithdrawViewModel.depositAmount(accNo, changeAmt, currentBal)
            .observe(viewLifecycleOwner, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            Timber.d(resource.toString())
                            if (resource.data != null) {
                                popToAccountDetailsPage()

                            }
                        }
                        Status.ERROR -> {
                            Timber.d(resource.message)
                        }
                        Status.LOADING -> {
                        }
                    }
                }
            })
    }

    /**
     * @summary method to setup observers for withdraw action updating UI State as per data changes
     * @param Int - account Number, Float - difference amount, Float - current balance
     */
    private fun withdrawObservers(accNo: Int, changeAmt: Float, currentBal: Float) {
        depositWithdrawViewModel.withdrawAmount(accNo, changeAmt, currentBal)
            .observe(viewLifecycleOwner, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            Timber.d(resource.toString())
                            if (resource.data != null) {
                                popToAccountDetailsPage()
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

    /**
     * @summary method to navigate back to initiation fragments depending on from where it started
     */
    private fun popToAccountDetailsPage() {
        if (fromListPage) {
            val action =
                DepositWithdrawFragmentDirections.actionDepositWithdrawFragmentToAccountsListFragment()
            Navigation.findNavController(binding.buttonDeposit).navigate(action)
        } else {
            val action =
                DepositWithdrawFragmentDirections.actionDepositWithdrawFragmentToAccountsDetailsFragment(
                    accNo
                )
            Navigation.findNavController(binding.buttonDeposit).navigate(action)
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}