package com.example.mealplanning.viewModels

import android.icu.util.Calendar
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mealplanning.repository.Repository
import com.example.mealplanning.ui.menu_creator.Dish
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import java.util.Locale

class CreatorViewModel: ViewModel() {

    private val repository=Repository()

    private var positionChoice=0

    private var dateCalendarMenuCreator="w"

    private var keyForDishes=""
    private var date=0
    private  val _statusOfChoose : MutableLiveData<String> = MutableLiveData()
    val statusOfChoose = _statusOfChoose

    private lateinit var requestResult : String



    fun setDateC(year: Int, month: Int, dayOfMonth: Int){
        val locale = Locale.getDefault()
        val currentDate = Calendar.getInstance(locale)
        val currentHour = currentDate.get(Calendar.HOUR_OF_DAY)

        val tomorrowDate = Calendar.getInstance()
        tomorrowDate.add(Calendar.DAY_OF_YEAR, 1)
        val chosenDate = Calendar.getInstance()
        chosenDate.set(year, month, dayOfMonth)

        if (requestResult != "chose") {
            Log.i("QWERTY", "Didn't choose")
            Log.i("QWERTY", "${chosenDate.get(Calendar.DAY_OF_MONTH)}")
            Log.i("QWERTY", "${currentDate.get(Calendar.DAY_OF_MONTH)}")
            Log.i("QWERTY", "${tomorrowDate.get(Calendar.DAY_OF_MONTH)}")
            if (
                (chosenDate.get(Calendar.YEAR) == tomorrowDate.get(Calendar.YEAR) &&
                        chosenDate.get(Calendar.MONTH) == tomorrowDate.get(Calendar.MONTH) &&
                        chosenDate.get(Calendar.DAY_OF_MONTH) == tomorrowDate.get(Calendar.DAY_OF_MONTH)
                        && currentHour >= 16) ||
                (chosenDate.get(Calendar.YEAR) == currentDate.get(Calendar.YEAR) &&
                        chosenDate.get(Calendar.MONTH) == currentDate.get(Calendar.MONTH) &&
                        chosenDate.get(Calendar.DAY_OF_MONTH) == currentDate.get(Calendar.DAY_OF_MONTH)
                        && currentHour <= 12)
            ) {
                statusOfChoose.postValue("choose")
            } else if (
                (currentDate.get(Calendar.YEAR) == chosenDate.get(Calendar.YEAR)
                        && currentDate.get(Calendar.MONTH) == chosenDate.get(Calendar.MONTH)
                        && currentDate.get(Calendar.DAY_OF_MONTH) == chosenDate.get(Calendar.DAY_OF_MONTH)
                        && currentHour >= 12) || chosenDate.before(currentDate)
            ) {
                statusOfChoose.postValue("alreadyLate")
            } else if (
                chosenDate.after(tomorrowDate) ||
                (chosenDate.get(Calendar.YEAR) == tomorrowDate.get(Calendar.YEAR) &&
                        chosenDate.get(Calendar.MONTH) == tomorrowDate.get(Calendar.MONTH) &&
                        chosenDate.get(Calendar.DAY_OF_MONTH) == tomorrowDate.get(Calendar.DAY_OF_MONTH)
                        && currentHour >= 16)
            ) {
                statusOfChoose.postValue("early")
            }
        } else {
            statusOfChoose.postValue("chose")
        }
    }

    /*
       )
     */

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

    fun downLoadDishForChoice(year: Int, month: Int, day: Int){
        repository.downLoadDishForChoiceCreator(dateCalendarMenuCreator) {
            requestResult = it
            setDateC(year, month, day)
        }
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