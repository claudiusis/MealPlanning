package com.example.mealplanning.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mealplanning.ui.menu_creator.Dish
import com.google.firebase.database.getValue
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class Repository {

    private val database=Firebase.database.reference

    private val allDish=ArrayList<Dish>().apply {
        add(Dish(0,"Макароны", "Ингредиенты",343))
        add(Dish(1,"Рис", "Ингредиенты2",343))
        add(Dish(2,"Гречка", "Ингредиенты",343))
    }

    private val studentChoice=ArrayList<Dish>()

    private val dishForChoiceLive:MutableLiveData<ArrayList<Dish>> by lazy { MutableLiveData<ArrayList<Dish>>() }
    private val dishForChoiceCopy=ArrayList<Dish>()

    private val dishStudentForChoiceLive:MutableLiveData<ArrayList<Dish>> by lazy {MutableLiveData<ArrayList<Dish>>()}
    private val dishStudentLive:MutableLiveData<ArrayList<Dish>> by lazy { MutableLiveData<ArrayList<Dish>>() }
    private val dishStudentCopyList=ArrayList<Dish>()

    fun downLoadAllDish(){
        database.child("dish").get().addOnSuccessListener {
            allDish.clear()
            if(it.exists()) {
                for (dishs in it.children) {
                    val dish=dishs.getValue(Dish::class.java)
                    allDish.add(dish!!)
                }
            }

        }
    }

    fun getAllDishList(): ArrayList<Dish> {
        return allDish
    }


    fun upLoadDishForChoice(date:String){
        database.child("Выбор/$date").setValue(dishForChoiceCopy)
    }


    fun addDish(){
        database.child("dish").setValue(allDish)
    }

    fun downLoadDishForChoice(date:String){
        Log.d("FENIX",date)
        database.child("Выбор").child(date).get().addOnSuccessListener {
            dishForChoiceCopy.clear()
            if(it.exists()){
                for (dishs in it.children){
                    val dish=dishs.getValue(Dish::class.java)
                    dishForChoiceCopy.add(dish!!)
                    dishForChoiceLive.postValue(dishForChoiceCopy)
                }
            }
            else{
                dishForChoiceCopy.add(Dish(100,"Будет скоро","Будет скоро"))
                dishForChoiceCopy.add(Dish(101,"Будет скоро","Будет скоро"))
                dishForChoiceCopy.add(Dish(102,"Будет скоро","Будет скоро"))
                dishForChoiceLive.postValue(dishForChoiceCopy)
            }
        }
    }


    fun downLoadDishForChoiceCreator(date:String){
        Log.d("FENIX",date)
        database.child("Выбор").child(date).get().addOnSuccessListener {
            dishForChoiceCopy.clear()
            if(it.exists()){
                for (dishs in it.children){
                    val dish=dishs.getValue(Dish::class.java)
                    dishForChoiceCopy.add(dish!!)
                    dishForChoiceLive.postValue(dishForChoiceCopy)
                }
            }
            else{
                dishForChoiceCopy.add(Dish(10000,"Выберите блюдо","Выберите блюдо"))
                dishForChoiceCopy.add(Dish(10001,"Выберите блюдо","Выберите блюдо"))
                dishForChoiceCopy.add(Dish(10002,"Выберите блюдо","Выберите блюдо"))
                dishForChoiceLive.postValue(dishForChoiceCopy)
            }
        }
    }

    fun getDishFromChoice(number:Int): Dish {
        return dishForChoiceLive.value!![number]
    }
    fun getListAfterChoiceLive(): MutableLiveData<ArrayList<Dish>> {
        return dishForChoiceLive
    }
    fun replaceDishForChoiceStudent(pos :Int, dish: Dish){
        dishStudentCopyList[pos]=dish
        dishStudentLive.postValue(dishStudentCopyList)
    }
    fun replaceDishForChoiceCreator(pos:Int,dish: Dish){
        dishForChoiceCopy[pos]=dish
        dishForChoiceLive.postValue(dishForChoiceCopy)
    }


    //ЛОГИКА ШКОЛЬНИКА

    fun downLoadMyChoice(date:String){
        Log.d("FENIX",date)
        database.child("ВыборШкольника").child(date).get().addOnSuccessListener {
            dishStudentCopyList.clear()
            if(it.exists()){
                for (dishs in it.children){
                    val dish=dishs.getValue(Dish::class.java)
                    dishStudentCopyList.add(dish!!)
                    dishStudentLive.postValue(dishForChoiceCopy)
                }
            }
            else{
                dishStudentCopyList.add(Dish(1000,"Выберите первое","Выберите первое"))
                dishStudentCopyList.add(Dish(1001,"Выберите второе","Выберите второе"))
                dishStudentCopyList.add(Dish(1002,"Выберите третье","Выберите третье"))
                dishStudentLive.postValue(dishStudentCopyList)
            }
        }
    }

    fun getStudentDishLive(): MutableLiveData<ArrayList<Dish>> {
        return dishStudentLive
    }


}