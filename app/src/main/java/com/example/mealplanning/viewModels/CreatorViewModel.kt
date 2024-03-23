package com.example.mealplanning.viewModels

import androidx.lifecycle.ViewModel
import com.example.mealplanning.repository.Repository
import com.example.mealplanning.ui.menu_creator.Dish

class CreatorViewModel: ViewModel() {

    private val repository=Repository()

    private var selectedDish=ArrayList<Dish>()
    private var positionChoice=0

    private var dateCalendarMenuCreator=""


    fun setDateCalendar(date :String){
        dateCalendarMenuCreator=date
    }

    fun getSelectedDish(): ArrayList<Dish> {
        return selectedDish
    }
    fun setSelectedDish(list:ArrayList<Dish>){
        selectedDish=list
    }
    fun getListAfterChoice(): ArrayList<Dish> {
        return repository.getListAfterChoice()
    }
    fun getDishFromChoice(number:Int): Dish {
        return repository.getDishFromChoice(number)
    }
    fun downLoadDishForChoice(){
        repository.downLoadDishForChoice()
    }
    fun setPositionChoice(pos:Int){
        positionChoice=pos
    }
    fun getPositionChoice(): Int {
        return positionChoice
    }
    fun addSelectedDish(dish: Dish, pos: Int){
        selectedDish[pos]=dish
    }

    fun upLoadSelectedDish(){
        repository.upLoadDishForChoice(selectedDish, dateCalendarMenuCreator)
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