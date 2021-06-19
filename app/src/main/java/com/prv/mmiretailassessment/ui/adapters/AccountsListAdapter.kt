package com.prv.mmiretailassessment.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.prv.mmiretailassessment.R
import com.prv.mmiretailassessment.databinding.ItemAccountsListBinding
import com.prv.mmiretailassessment.models.AccountDetails
import com.prv.mmiretailassessment.ui.AccountsListFragmentDirections
import kotlinx.android.synthetic.main.item_accounts_list.view.*

class AccountsListAdapter(var accountListMap: MutableMap<String, AccountDetails>): RecyclerView.Adapter<AccountsListAdapter.AccountsListViewHolder>(), AccountsListClickListener {
    class AccountsListViewHolder(var view: ItemAccountsListBinding): RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AccountsListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        //val view = inflater.inflate(R.layout.item_dog, parent, false)
        val view = DataBindingUtil.inflate<ItemAccountsListBinding>(inflater, R.layout.item_accounts_list, parent, false)
        return AccountsListViewHolder(view)
    }

    override fun onBindViewHolder(holder: AccountsListViewHolder, position: Int) {
        holder.view.accountno = accountListMap.keys.elementAt(position)
        holder.view.listener = this
    }

    override fun getItemCount() = accountListMap.size

    fun updateAccountsList(newAccountListMap: Map<String, AccountDetails>){
        accountListMap.clear()
        accountListMap.putAll(newAccountListMap)
        notifyDataSetChanged()
    }

    override fun onAccountItemClicked(v: View) {
        val accountno = v.accountNumberId.text.toString()
        val action = AccountsListFragmentDirections.actionAccountsListFragmentToAccountsDetailsFragment()
        action.accountNo = accountno
        Navigation.findNavController(v).navigate(action)
    }

}

interface AccountsListClickListener {
    fun onAccountItemClicked(v: View)
}
