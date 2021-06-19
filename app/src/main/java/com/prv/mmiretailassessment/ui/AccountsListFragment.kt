package com.prv.mmiretailassessment.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.prv.mmiretailassessment.R
import com.prv.mmiretailassessment.databinding.FragmentAccountsListBinding
import com.prv.mmiretailassessment.singletons.User
import com.prv.mmiretailassessment.ui.adapters.AccountsListAdapter
import com.prv.mmiretailassessment.utils.Status
import com.prv.mmiretailassessment.viewmodels.AccountsListViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class AccountsListFragment : Fragment() {

    val accountsListViewModel: AccountsListViewModel by viewModel()

    private lateinit var accountListAdapter: AccountsListAdapter

    private var _binding: FragmentAccountsListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle? ): View? {
        _binding = FragmentAccountsListBinding.inflate(inflater, container, false)
        setupObservers()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        binding.buttonFirst.setOnClickListener {
//            findNavController().navigate(R.id.action_accountsListFragment_to_accountsDetailsFragment)
//        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun populateAccountList(){
        accountListAdapter = AccountsListAdapter( User.UserDetails.accounts.toMutableMap() )
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
                            populateAccountList()
                        }
                        binding.progressBar.visibility = View.GONE
                        binding.listError.visibility = View.GONE
                        //loginSuccess()
                        //resource.data?.let { users -> retrieveList(users) }
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


/*
class ListFragment : Fragment() {

    private val dogsListAdapter = DogsListAdapter( arrayListOf() )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.refresh()

        dogsList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = dogsListAdapter
        }

        refreshLayout.setOnRefreshListener {
            dogsList.visibility = View.GONE
            listError.visibility = View.GONE
            progressBar.visibility = View.VISIBLE
            viewModel.refreshBypassCache()
            refreshLayout.isRefreshing = false
        }

        observeViewModel()
    }

    fun observeViewModel(){
        viewModel.dogs.observe(this, Observer { dogs ->
            dogs?.let {
                dogsList.visibility = View.VISIBLE
                listError.visibility = View.GONE
                dogsListAdapter.updateDogsList(dogs)
            }
        })

        viewModel.dogsError.observe(this, Observer { isError ->
            isError?.let {
                listError.visibility  = if(it) View.VISIBLE else View.GONE
            }
        })

        viewModel.loading.observe(this, Observer { isLoading ->
            isLoading?.let {
                progressBar.visibility = if(it) View.VISIBLE else View.GONE
                if(it){
                    listError.visibility = View.GONE
                    dogsList.visibility = View.GONE
                }
            }
        })

    }

}
*/
