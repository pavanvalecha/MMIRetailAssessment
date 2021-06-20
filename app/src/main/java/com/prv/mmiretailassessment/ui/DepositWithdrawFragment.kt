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
import com.prv.mmiretailassessment.databinding.FragmentDepositWithdrawBinding
import com.prv.mmiretailassessment.singletons.User
import com.prv.mmiretailassessment.utils.Status
import com.prv.mmiretailassessment.viewmodels.AccountsListViewModel
import com.prv.mmiretailassessment.viewmodels.DepositWithdrawViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber


/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
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
            updateBalance()
        }
    }

    private fun updateBalance(){
        val changeAmt = binding.amountEdittext.text.toString().toFloat()
        if (depositMode){
            depositObservers(accNo, changeAmt, currentBal)
        } else {
            withdrawObservers(accNo, changeAmt, currentBal)
        }
    }

    private fun depositObservers(accNo:Int, changeAmt:Float, currentBal:Float) {
        depositWithdrawViewModel.depositAmount(accNo, changeAmt, currentBal)
            .observe(viewLifecycleOwner, Observer {
                it?.let { resource ->
                    when (resource.status) {
                        Status.SUCCESS -> {
                            Timber.d(resource.toString())
                            if (resource.data != null) {
                                popToAccountDetailsPage()

                            }
                            //binding.progressBar.visibility = View.GONE
                            //binding.listError.visibility = View.GONE
                            //loginSuccess()
                            //resource.data?.let { users -> retrieveList(users) }
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

        private fun withdrawObservers(accNo:Int, changeAmt:Float, currentBal:Float) {
            depositWithdrawViewModel.withdrawAmount(accNo, changeAmt, currentBal)
                .observe(viewLifecycleOwner, Observer {
                    it?.let { resource ->
                        when (resource.status) {
                            Status.SUCCESS -> {
                                Timber.d(resource.toString())
                                if (resource.data != null) {
                                    popToAccountDetailsPage()
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

    private fun popToAccountDetailsPage(){
        val action = DepositWithdrawFragmentDirections.actionDepositWithdrawFragmentToAccountsDetailsFragment(accNo)
        Navigation.findNavController(binding.buttonDeposit).navigate(action)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}