package com.example.mealplanning.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mealplanning.ui.menu_creator.Dish
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.util.HashMap

class Repository {

    private val database=Firebase.database.reference

  /*  private val allDishMap : ArrayList<Dish> = arrayListOf(
            Dish(0, "Щи", "Капуста, картофель, морковь", 343, "Первое"),
            Dish(1, "Борщ", "Капуста, картофель, морковь, свекла", 343, "Первое"),
            Dish(2, "Солянка", "куриная грудка, копчёная и варёная колбасы, оливки, маслины, лимон", 343, "Первое"),
            Dish(3, "Плов", "Курица, рис, морковь", 343, "Второе"),
            Dish(4, "Жаркое", "Свинина, картофель, морковь", 343, "Второе"),
            Dish(5, "Макароны по-флотски", "Говяжий фарш, макароны", 343, "Второе"),
            Dish(6, "Кофе", "Капучино", 343, "Напиток"),
            Dish(7, "Чай", "Чёрный", 343, "Напиток"),
            Dish(8, "Сок", "Яблочный", 343,"Напиток")
    )
*/

    private val allDishMap = HashMap<String, ArrayList<Dish>>().apply {
        put(
            "First", arrayListOf(
                Dish(0, "Щи", "Капуста, картофель, морковь", 343, "Первое"),
                Dish(1, "Борщ", "Капуста, картофель, морковь, свекла", 343, "Первое"),
                Dish(2, "Солянка", "куриная грудка, копчёная и варёная колбасы, оливки, маслины, лимон", 343, "Первое")
            )
        )
        put(
            "Second", arrayListOf(
                Dish(3, "Плов", "Курица, рис, морковь", 343, "Второе"),
                Dish(4, "Жаркое", "Свинина, картофель, морковь", 343, "Второе"),
                Dish(5, "Макароны по-флотски", "Говяжий фарш, макароны", 343, "Второе")
            )
        )
        put(
            "Drink", arrayListOf(
                Dish(6, "Кофе", "Капучино", 343, "Напиток"),
                Dish(7, "Чай", "Чёрный", 343, "Напиток"),
                Dish(8, "Сок", "Яблочный", 343,"Напиток")
            )
        )
    }


    //Метод добавления блюда
    fun addDish(){
        for ( item in allDishMap.keys){
            database.child("dish").child(item).setValue(allDishMap[item])
        }

    }



    private val allDish=ArrayList<Dish>()
    private val studentChoice=ArrayList<Dish>()

    private val dishForChoiceLive:MutableLiveData<ArrayList<Dish>> by lazy { MutableLiveData<ArrayList<Dish>>() }
    private val dishForChoiceCopy=ArrayList<Dish>()

    private val dishStudentForChoiceLive:MutableLiveData<ArrayList<Dish>> by lazy {MutableLiveData<ArrayList<Dish>>()}
    private val dishStudentLive:MutableLiveData<ArrayList<Dish>> by lazy { MutableLiveData<ArrayList<Dish>>() }
    private val dishStudentCopyList=ArrayList<Dish>()

    fun downLoadAllDish(){
        database.child("dish").get().addOnSuccessListener {
            if(it.exists()) {
                for (types in it.children) {
                    for (dishes in types.children) {
                        val dish = dishes.getValue(Dish::class.java)
                        allDish.add(dish!!)
                    }
                }
            }

        }
    }

    fun getFromAllDish(number: Int) : Dish {
        return allDish[number]
    }

    fun getAllDishList(): ArrayList<Dish> {
        return allDish
    }


    fun upLoadDishForChoice(date:String){
        database.child("Выбор/$date").setValue(dishForChoiceCopy)
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
                dishForChoiceCopy.add(Dish(10000,"Выберите первое блюдо",""))
                dishForChoiceCopy.add(Dish(10001,"Выберите второе блюдо",""))
                dishForChoiceCopy.add(Dish(10002,"Выберите напиток",""))
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


    //


}