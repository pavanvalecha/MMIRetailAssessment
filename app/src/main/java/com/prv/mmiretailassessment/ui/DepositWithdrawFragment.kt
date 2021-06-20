package com.prv.mmiretailassessment.ui

import android.os.Bundle
import timber.log.Timber
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import com.prv.mmiretailassessment.R
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.prv.mmiretailassessment.utils.Status
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.prv.mmiretailassessment.viewmodels.DepositWithdrawViewModel
import com.prv.mmiretailassessment.databinding.FragmentDepositWithdrawBinding


class DepositWithdrawFragment : Fragment() {

    val depositWithdrawViewModel: DepositWithdrawViewModel by viewModel()

    private var _binding: FragmentDepositWithdrawBinding? = null
    private val binding get() = _binding!!
    val args: DepositWithdrawFragmentArgs by navArgs()

    private var depositMode = false
    private var currentBal: Float = 0.0F
    private var accNo: Int = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentDepositWithdrawBinding.inflate(inflater, container, false)
        depositMode = args.deposit
        currentBal = args.currentBal
        accNo = args.accNo
        setupUI()
        return binding.root
    }

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

    private fun updateBalance() {
        val changeAmt = binding.amountEdittext.text.toString().toFloat()
        if (depositMode) {
            depositObservers(accNo, changeAmt, currentBal)
        } else {
            withdrawObservers(accNo, changeAmt, currentBal)
        }
    }

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

    private fun popToAccountDetailsPage() {
        val action =
            DepositWithdrawFragmentDirections.actionDepositWithdrawFragmentToAccountsDetailsFragment(
                accNo
            )
        Navigation.findNavController(binding.buttonDeposit).navigate(action)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}