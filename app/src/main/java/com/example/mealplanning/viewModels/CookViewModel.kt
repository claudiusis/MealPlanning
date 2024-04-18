package com.example.mealplanning.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mealplanning.repository.Repository
import com.example.mealplanning.ui.menu_creator.Dish

class CookViewModel : ViewModel() {

    private val repository= Repository()

    private var showMore:Int=0

    private var dateCalendarCook="w"
    private var positionChoice=0

    fun getFromAllDish(number: Int): Dish {
        return repository.getFromAllDish(number)
    }
    fun setPositionChoice(pos:Int){
        positionChoice=pos
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

    fun getDishFromChoice(number:Int): Dish {
        return repository.getDishFromChoice(number)
    }

    fun downLoadDishForChoice(){
        repository.downLoadDishForChoice(dateCalendarCook)
    }

    fun downLoadMyChoice(){
        repository.downLoadMyChoice(dateCalendarCook)
    }

    fun getStudentDishLive(): MutableLiveData<ArrayList<Dish>> {
        return repository.getStudentDishLive()
    }

    fun getDateCalendar(): String {
        return dateCalendarCook
    }

    fun setDateCalendar(date:String){
        dateCalendarCook=date
    }

    fun getDishForChoice(): MutableLiveData<ArrayList<Dish>> {
        return repository.getListAfterChoiceLive()
    }
    fun replaceDishForChoice(pos : Int, dish: Dish){
        repository.replaceDishForChoiceStudent(pos, dish)
    }

    fun getListAfterChoiceLive(): MutableLiveData<ArrayList<Dish>> {
        return repository.getListAfterChoiceLive()
    }

    fun getTotalStudentDishes(): Int {
        return repository.getTotalDishesCountForStudentChoice()
    }

    fun downloadDishToCook(date : String) {
        repository.downLoadDishToCook(date)
    }


}