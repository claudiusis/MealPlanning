package com.example.mealplanning.ui.admin

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealplanning.R
import com.example.mealplanning.databinding.FragmentAdminAccountsBinding
import com.example.mealplanning.ui.menu_creator.AccountsAdminAdapter
import com.example.mealplanning.viewModels.AdminViewModel
import com.example.mealplanning.viewModels.CreatorViewModel

class AdminAccountsFragment : Fragment() {

    private var _binding:FragmentAdminAccountsBinding?=null
    private val mBinding get()=_binding!!

    private val viewModelAdmin: AdminViewModel by activityViewModels<AdminViewModel>()

    private lateinit var recyclerAccounts: AccountsAdminAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding=FragmentAdminAccountsBinding.inflate(inflater, container, false)

        recyclerAccounts= AccountsAdminAdapter(this,viewModelAdmin)

        mBinding.adminAccountsRecycler.layoutManager=LinearLayoutManager(context)
        mBinding.adminAccountsRecycler.adapter=recyclerAccounts

        recyclerAccounts.notesList=viewModelAdmin.getAllAccountsCopy()

        mBinding.adminCreateNewAccountBtn.setOnClickListener {
            findNavController().navigate(R.id.action_adminAccountsFragment_to_adminCreateAccount)
        }

        return mBinding.root
    }

    override fun onResume() {
        super.onResume()
        recyclerAccounts.notesList=viewModelAdmin.getAllAccountsCopy()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}