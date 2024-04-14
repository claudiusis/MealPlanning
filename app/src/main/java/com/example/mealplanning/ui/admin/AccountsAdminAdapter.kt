package com.example.mealplanning.ui.menu_creator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.mealplanning.MyDiffUtil
import com.example.mealplanning.R
import com.example.mealplanning.databinding.AdminItemBinding
import com.example.mealplanning.databinding.DishViewHolderBinding
import com.example.mealplanning.ui.admin.AccountsData
import com.example.mealplanning.ui.admin.AdminAccountsFragment
import com.example.mealplanning.viewModels.AdminViewModel
import com.example.mealplanning.viewModels.CreatorViewModel

class AccountsAdminAdapter(
    private val fragment: AdminAccountsFragment,
    private val viewModelCreator: AdminViewModel) : RecyclerView.Adapter<AccountsAdminAdapter.AdminViewHolder>() {


    var notesList = listOf<AccountsData>()
        set(value) {
            val callback = MyDiffUtil(oldArray = field, newArray = value,
                {old, new ->  old.id==new.id})
            field = value
            val diffResult = DiffUtil.calculateDiff(callback)
            diffResult.dispatchUpdatesTo(this)
        }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdminViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.admin_item, parent, false)
        return AdminViewHolder(view)
    }

    override fun onBindViewHolder(holder: AdminViewHolder, position: Int) {
        holder.onBind(notesList[position])

    }

    override fun getItemCount()=notesList.size

    class AdminViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private var _binding: AdminItemBinding
        val mBinding get()=_binding

        init {
            _binding=AdminItemBinding.bind(itemView)
        }

        private val id: TextView = itemView.findViewById(R.id.accounts_id)
        private val role: TextView = itemView.findViewById(R.id.accounts_role)
        private val workerName: TextView=mBinding.accountsWorkerName

        fun onBind(items: AccountsData){
            id.text= items.id.toString()
            role.text=items.role
            workerName.text=items.name
        }
    }
}