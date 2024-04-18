package com.example.mealplanning.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mealplanning.repository.Repository
import com.example.mealplanning.ui.controller.Product

class ControllerViewModel:ViewModel() {
    private val repository= Repository()


    fun createSupply(productName:String,count:Int){
        repository.createSupply(productName,count)
    }
    fun getAllProductsLive(): MutableLiveData<ArrayList<Product>> {
        return repository.getAllProductsLive()
    }

    fun downLoadAllProducts(){
        repository.downLoadAllProducts()
    }

}