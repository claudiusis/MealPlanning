package com.example.mealplanning.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mealplanning.repository.Repository
import com.example.mealplanning.ui.menu_creator.Dish

class CreatorViewModel: ViewModel() {

    private val repository=Repository()

    private var positionChoice=0

    private var dateCalendarMenuCreator="w"

    private var keyForDishes=""


    fun setKeyForDishes(key: String){
        keyForDishes=key
    }
    fun getKeyDishForChoice(): String {
        return keyForDishes
    }

    fun replaceDishForChoice(pos : Int, dish: Dish){
        repository.replaceDishForChoiceCreator(keyForDishes,pos, dish)
    }

    fun setDateCalendar(date :String){
        dateCalendarMenuCreator=date
    }

    fun getDateCalendar(): String {
        return dateCalendarMenuCreator
    }


    fun getListAfterChoiceLive(): MutableLiveData<HashMap<String, ArrayList<Dish>>> {
        return repository.getListAfterChoiceLive()
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


    fun upLoadSelectedDish(){
        repository.upLoadDishForChoice(dateCalendarMenuCreator)
    }

    fun addDish(){
        repository.addDish()
    }


    fun downLoadAllDish(){
        repository.downLoadAllDish()
    }


    fun getAllDishList(): ArrayList<Dish>? {
        return repository.getAllDishList(keyForDishes)
    }
}