package com.example.mealplanning.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mealplanning.repository.Repository
import com.example.mealplanning.ui.menu_creator.Dish

class CreatorViewModel: ViewModel() {

    private val repository=Repository()

    private var selectedDish=ArrayList<Dish>()
    private var positionChoice=0

    private var dateCalendarMenuCreator="w"


    fun getFromAllDish(number: Int): Dish {
        return repository.getFromAllDish(number)
    }
    fun replaceDishForChoice(pos : Int, dish: Dish){
        repository.replaceDishForChoiceCreator(pos, dish)
    }

    fun setDateCalendar(date :String){
        dateCalendarMenuCreator=date
    }

    fun getDateCalendar(): String {
        return dateCalendarMenuCreator
    }

    fun getSelectedDish(): ArrayList<Dish> {
        return selectedDish
    }
    fun setSelectedDish(list:ArrayList<Dish>){
        selectedDish=list
    }
    fun getListAfterChoiceLive(): MutableLiveData<ArrayList<Dish>> {
        return repository.getListAfterChoiceLive()
    }
    fun getDishFromChoice(number:Int): Dish {
        return repository.getDishFromChoice(number)
    }
    fun downLoadDishForChoice(){
        repository.downLoadDishForChoiceCreator(dateCalendarMenuCreator)
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
        repository.upLoadDishForChoice(dateCalendarMenuCreator)
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