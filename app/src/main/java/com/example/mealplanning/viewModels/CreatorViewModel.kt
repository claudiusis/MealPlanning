package com.example.mealplanning.viewModels

import androidx.lifecycle.ViewModel
import com.example.mealplanning.repository.Repository
import com.example.mealplanning.ui.menu_creator.Dish

class CreatorViewModel: ViewModel() {

    private val repository=Repository()

    private val selectedDish=ArrayList<Dish>()

    fun addSelectedDish(dish: Dish){
        selectedDish.add(dish)
    }

    fun upLoadSelectedDish(){
        repository.upLoadDishForChoice(selectedDish)
    }

    fun addDish(){
        repository.addDish()
    }


    fun downLoadAllDish(){
        repository.downLoadAllDish()
    }


    fun getAllDishList(): ArrayList<Dish> {
        return repository.getAllDishList()
    }
}