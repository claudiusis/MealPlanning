package com.example.mealplanning.viewModels

import androidx.lifecycle.ViewModel
import com.example.mealplanning.repository.Repository
import com.example.mealplanning.ui.admin.AccountsData

class AdminViewModel : ViewModel() {
    private val repository=Repository()



    fun getAllAccountsCopy(): ArrayList<AccountsData> {
        return repository.getAllAccountsCopy()
    }

    fun downLoadAllAccounts(){
        repository.downLoadAllAccounts()
    }

    fun createAccount(account : AccountsData){
        repository.createAccount(account)
    }


}