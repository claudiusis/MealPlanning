package com.example.mealplanning.viewModels

import androidx.lifecycle.ViewModel
import com.example.mealplanning.repository.Repository
import com.example.mealplanning.ui.menu_creator.Dish

class StudentViewModel:ViewModel() {

    private val repository=Repository()

    private var showMore:Int=0


    fun setShowMore(number:Int){
        showMore=number
    }
    fun getShowMore(): Int {
        return showMore
    }

    fun getDishFromChoice(number:Int): Dish {
        return repository.getDishFromChoice(number)
    }

    fun downLoadDishForChoice(){
        repository.downLoadDishForChoice("data")
    }

}