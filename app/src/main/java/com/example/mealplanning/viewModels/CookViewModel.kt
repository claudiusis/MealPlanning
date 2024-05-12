package com.example.mealplanning.viewModels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mealplanning.repository.Repository
import com.example.mealplanning.ui.cook.DishToCook
import com.example.mealplanning.ui.menu_creator.Dish

class CookViewModel : ViewModel() {

    private val repository= Repository()

    private var showMore:Int=0

    private var dateCalendarCook="w"
    private var positionChoice=0

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



    fun getListAfterChoiceLive(): MutableLiveData<HashMap<String, ArrayList<Dish>>> {
        return repository.getListAfterChoiceLive()
    }


    fun downloadDishToCook(date : String) {
        repository.downLoadDishToCook(date)
    }


    fun getDishToCookLiveData() : MutableLiveData<ArrayList<DishToCook>>{
        return repository.getDishToCookLiveData()
    }
}