package com.example.mealplanning.repository

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

    private val dishForChoice=ArrayList<Dish>()

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


    fun upLoadDishForChoice(listDish: ArrayList<Dish>){
        database.child("Выбор").setValue(listDish)
    }


    fun addDish(){
        database.child("dish").setValue(allDish)
    }

    fun downLoadDishForChoice(){
        database.child("Выбор").get().addOnSuccessListener {
            dishForChoice.clear()
            if(it.exists()){
                for (dishs in it.children){
                    val dish=dishs.getValue(Dish::class.java)
                    dishForChoice.add(dish!!)
                }
            }
        }
    }

    fun getDishFromChoice(number:Int): Dish {
        return dishForChoice[number]
    }




}