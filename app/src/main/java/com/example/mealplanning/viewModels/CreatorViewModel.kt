package com.example.mealplanning.viewModels

import android.icu.util.Calendar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mealplanning.repository.Repository
import com.example.mealplanning.ui.menu_creator.Dish

class CreatorViewModel: ViewModel() {

    private val repository=Repository()

    private var positionChoice=0

    private var dateCalendarMenuCreator="w"

    private var keyForDishes=""
    private var date=0
    private var isAvailableToOpen=true



    fun setDateC(year: Int, month: Int, dayOfMonth: Int){
        val currentDate=Calendar.getInstance()
        val currentHour=currentDate.get(Calendar.HOUR)

        val tomorrowDate = Calendar.getInstance()
        tomorrowDate.add(Calendar.DAY_OF_YEAR, 1)
        val choosedDate = Calendar.getInstance()
        choosedDate.set(year, month, date)




       if (
           choosedDate.get(Calendar.YEAR) == tomorrowDate.get(Calendar.YEAR) &&
           choosedDate.get(Calendar.MONTH) == tomorrowDate.get(Calendar.MONTH) &&
           choosedDate.get(Calendar.DAY_OF_MONTH) == tomorrowDate.get(Calendar.DAY_OF_MONTH)
           && currentHour>=16
       ) {
           isAvailableToOpen = true
       } else if (
           currentDate.get(Calendar.YEAR) == choosedDate.get(Calendar.YEAR)
           && currentDate.get(Calendar.MONTH) == choosedDate.get(Calendar.MONTH)
           && currentDate.get(Calendar.DAY_OF_MONTH) == choosedDate.get(Calendar.DAY_OF_MONTH)
           && currentHour <=12
       ) {
           isAvailableToOpen = false
       }

    }

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
        repository.downLoadDishForChoiceCreator(dateCalendarMenuCreator, isAvailableToOpen)
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