package com.prv.mmiretailassessment.ui.adapters

import android.view.View
import android.view.ViewGroup
import android.view.LayoutInflater
import com.prv.mmiretailassessment.R
import androidx.navigation.Navigation
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.prv.mmiretailassessment.models.AccountDetailsModel
import kotlinx.android.synthetic.main.item_accounts_list.view.*
import com.prv.mmiretailassessment.ui.AccountsListFragmentDirections
import com.prv.mmiretailassessment.databinding.ItemAccountsListBinding

class AccountsListAdapter(var accountListMap: MutableMap<String, AccountDetailsModel>) :
    RecyclerView.Adapter<AccountsListAdapter.AccountsListViewHolder>(), AccountsListClickListener {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountsListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ItemAccountsListBinding>(
            inflater,
            R.layout.item_accounts_list,
            parent,
            false
        )
        return AccountsListViewHolder(view)
    }

    override fun onBindViewHolder(holder: AccountsListViewHolder, position: Int) {
        holder.view.accountno = accountListMap.keys.elementAt(position)
        holder.view.listener = this
    }

    override fun getItemCount() = accountListMap.size

    fun updateAccountsList(newAccountListMap: Map<String, AccountDetailsModel>) {
        accountListMap.clear()
        accountListMap.putAll(newAccountListMap)
        notifyDataSetChanged()
    }

    override fun onAccountItemClicked(v: View) {
        val accountno = v.accountNumberId.text.toString().toInt()
        val action =
            AccountsListFragmentDirections.actionAccountsListFragmentToAccountsDetailsFragment(
                accountno
            )
        Navigation.findNavController(v).navigate(action)
    }

    class AccountsListViewHolder(var view: ItemAccountsListBinding) :
        RecyclerView.ViewHolder(view.root)
}

interface AccountsListClickListener {
    fun onAccountItemClicked(v: View)
}
