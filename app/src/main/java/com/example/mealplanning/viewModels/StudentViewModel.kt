package com.example.mealplanning.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mealplanning.repository.Repository
import com.example.mealplanning.ui.menu_creator.Dish

class StudentViewModel:ViewModel() {

    private val repository=Repository()

    private var showMore:Int=0

    private var dateCalendarStudent="w"
    private var positionChoice=0

    private var keyForDishes=""
    private var keyType=""

    fun getKeyType(): String {
        return keyType
    }

    fun setPositionChoice(pos:Int){
        positionChoice=pos
        when(pos){
            0->keyType="First"
            1->keyType="Second"
            2->keyType="Drink"
        }
    }
    fun getPositionChoice(): Int {
        return positionChoice
    }
    fun setShowMore(number:Int){
        showMore=number
    }
    fun getShowMore(): Int {
        return showMore
    }


    fun downLoadDishForChoice(){
        repository.downLoadDishForChoice(dateCalendarStudent)
    }

    fun downLoadMyChoice(){
        repository.downLoadMyChoice(dateCalendarStudent)
    }

    fun upLoadStudentDish(){
        repository.upLoadStudentDish(dateCalendarStudent)
    }

    fun getStudentDishLive(): MutableLiveData<ArrayList<Dish>> {
        return repository.getStudentDishLive()
    }

    fun getDateCalendar(): String {
        return dateCalendarStudent
    }

    fun setDateCalendar(date:String){
        dateCalendarStudent=date
    }

    fun getDishForChoice(): MutableLiveData<HashMap<String, ArrayList<Dish>>> {
        return repository.getListAfterChoiceLive()
    }
    fun replaceDishForChoice(pos : Int, dish: Dish){
        repository.replaceDishForChoiceStudent(pos, dish)
    }
    fun getDishById(id: Int): Dish? {
        return repository.getDishById(id)
    }

    fun setKeyForDishes(key: String){
        keyForDishes=key
    }

}