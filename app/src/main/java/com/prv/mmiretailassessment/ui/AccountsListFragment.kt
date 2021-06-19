package com.prv.mmiretailassessment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.prv.mmiretailassessment.R
import com.prv.mmiretailassessment.databinding.FragmentAccountsListBinding
import com.prv.mmiretailassessment.utils.Status
import com.prv.mmiretailassessment.viewmodels.AccountsListViewModel
import com.prv.mmiretailassessment.viewmodels.LoginViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AccountsListFragment : Fragment() {

    val accountsListViewModel: AccountsListViewModel by viewModel()
    private var _binding: FragmentAccountsListBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAccountsListBinding.inflate(inflater, container, false)
        setupObservers()
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.action_accountsListFragment_to_accountsDetailsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setupObservers() {
        accountsListViewModel.listUsers().observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        Timber.d(resource.toString())
                        //binding.loading.visibility = View.GONE
                        //loginSuccess()
                        //resource.data?.let { users -> retrieveList(users) }
                    }
                    Status.ERROR -> {
                        //binding.loading.visibility = View.GONE
                        Timber.d(resource.message)
                    }
                    Status.LOADING -> {
                        //binding.loading.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

}