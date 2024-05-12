package com.example.mealplanning.ui.menu_creator

data class Dish(val id:Int?=null,
                val name : String?=null,
                val ingredients : String?=null,
                val image : Int?=null,
                var count : Int = 0)