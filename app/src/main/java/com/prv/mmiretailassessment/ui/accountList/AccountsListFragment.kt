package com.prv.mmiretailassessment.ui.accountList

import timber.log.Timber
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import androidx.lifecycle.Observer
import com.prv.mmiretailassessment.R
import androidx.fragment.app.Fragment
import com.prv.mmiretailassessment.utils.Status
import com.prv.mmiretailassessment.singletons.User
import androidx.recyclerview.widget.LinearLayoutManager
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.prv.mmiretailassessment.viewModels.AccountsListViewModel
import com.prv.mmiretailassessment.databinding.FragmentAccountsListBinding

class AccountsListFragment : Fragment() {

    private val binding get() = _binding!!
    private var _binding: FragmentAccountsListBinding? = null
    private val accountsListViewModel: AccountsListViewModel by viewModel()

    private lateinit var accountListAdapter: AccountsListAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAccountsListBinding.inflate(inflater, container, false)
        setupObservers()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun setWelcomeMsg(firstName: String, lastName:String){
        binding.headerView.text = String.format(resources.getString(R.string.welcome_text), firstName, lastName)
    }

    private fun populateAccountList() {
        accountListAdapter = AccountsListAdapter(User.UserDetails.accounts.toMutableMap())
        binding.accountsList.visibility = View.VISIBLE
        binding.accountsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = accountListAdapter
        }
    }

    private fun setupObservers() {
        accountsListViewModel.listUsers().observe(viewLifecycleOwner, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        Timber.d(resource.toString())
                        if (resource.data != null) {
                            User.UserDetails = resource.data
                            setWelcomeMsg(User.UserDetails.Name, User.UserDetails.LastName)
                            populateAccountList()
                        }
                        binding.progressBar.visibility = View.GONE
                        binding.listError.visibility = View.GONE
                    }
                    Status.ERROR -> {
                        binding.progressBar.visibility = View.GONE
                        binding.listError.visibility = View.VISIBLE
                        Timber.d(resource.message)
                    }
                    Status.LOADING -> {
                        binding.progressBar.visibility = View.VISIBLE
                        binding.listError.visibility = View.GONE
                    }
                }
            }
        })
    }

}