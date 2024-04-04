package com.example.mealplanning.repository

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.example.mealplanning.ui.admin.AccountsData
import com.example.mealplanning.ui.menu_creator.Dish
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import java.text.SimpleDateFormat
import java.util.Date

class Repository {

    private val database=Firebase.database.reference

/*    private val allDish=ArrayList<Dish>().apply {
        add(Dish(0,"Макароны", "Ингредиенты",343))
        add(Dish(1,"Рис", "Ингредиенты2",343))
        add(Dish(2,"Гречка", "Ингредиенты",343))
    }*/

    private val allDish = HashMap<String, ArrayList<Dish>>()

    private val studentChoice=ArrayList<Dish>()

    private val dishForChoiceLive:MutableLiveData<HashMap<String,ArrayList<Dish>>> by lazy { MutableLiveData<HashMap<String,ArrayList<Dish>>>() }
    private val dishForChoiceCopy=HashMap<String,ArrayList<Dish>>()

    private val dishStudentForChoiceLive:MutableLiveData<ArrayList<Dish>> by lazy {MutableLiveData<ArrayList<Dish>>()}
    private val dishStudentLive:MutableLiveData<ArrayList<Dish>> by lazy { MutableLiveData<ArrayList<Dish>>() }
    private val dishStudentCopyList=ArrayList<Dish>()


    private val allAccountsLive : MutableLiveData<ArrayList<AccountsData>> by lazy {MutableLiveData<ArrayList<AccountsData>>()}
    private val allAccountsCopy=ArrayList<AccountsData>()

    private val allDishMap = HashMap<String, ArrayList<Dish>>().apply {    put(
        "First", arrayListOf(            Dish(0, "Щи", "Капуста, картофель, морковь", 343),
            Dish(1, "Борщ", "Капуста, картофель, морковь, свекла", 343),            Dish(2, "Солянка", "куриная грудка, копчёная и варёная колбасы, оливки, маслины, лимон", 343)
        )    )
        put(        "Second", arrayListOf(
            Dish(3, "Плов", "Курица, рис, морковь", 343),            Dish(4, "Жаркое", "Свинина, картофель, морковь", 343),
            Dish(5, "Макароны по-флотски", "Говяжий фарш, макароны", 343)        )
        )
        put(
                "Drink", arrayListOf(            Dish(6, "Кофе", "Капучино", 343),
        Dish(7, "Чай", "Чёрный", 343),            Dish(8, "Сок", "Яблочный", 343)
        )    )
    }
    //Метод добавления блюда
    fun addDish(){    for ( item in allDishMap.keys){
        database.child("dish").child(item).setValue(allDishMap[item])    }
    }



    fun downLoadAllDish(){
        database.child("dish").get().addOnSuccessListener {
            allDish.clear()
            if(it.exists()) {
                for (types in it.children) {
                    allDish[types.key.toString()] = arrayListOf()
                    for (dishes in types.children){
                        val dish=dishes.getValue(Dish::class.java)
                        allDish[types.key]?.add(dish!!)
                    }
                    Log.d("RRR",allDish.toString())
                }
            }

        }
    }

    fun getAllDishList(key:String): ArrayList<Dish>? {
        return allDish[key]
    }


    fun upLoadDishForChoice(date:String){
        database.child("Выбор/$date").setValue(dishForChoiceCopy)
    }


//    fun addDish(){
//        database.child("dish").setValue(allDish)
//    }

    fun downLoadDishForChoice(date:String){
        Log.d("FENIX",date)
        database.child("Выбор").child(date).get().addOnSuccessListener {
            dishForChoiceCopy.clear()
            if(it.exists()){
                for(types in it.children){
                    dishForChoiceCopy[types.key.toString()]= arrayListOf()
                    for (dishes in types.children){
                        val dish=dishes.getValue(Dish::class.java)
                        dishForChoiceCopy[types.key]?.add(dish!!)
                        dishForChoiceLive.postValue(dishForChoiceCopy)
                    }
                }
            }
        }
    }


    fun downLoadDishForChoiceCreator(date:String){
        Log.d("FENIX",date)
        database.child("Выбор").child(date).get().addOnSuccessListener {
            dishForChoiceCopy.clear()
            if(it.exists()){
                for(types in it.children){
                    dishForChoiceCopy[types.key.toString()]= arrayListOf()
                    for (dishes in types.children){
                        val dish=dishes.getValue(Dish::class.java)
                        dishForChoiceCopy[types.key]?.add(dish!!)
                        dishForChoiceLive.postValue(dishForChoiceCopy)
                    }
                }
            }
            else{
                dishForChoiceCopy.put("First", arrayListOf(
                    Dish(0, "Выберите первое"),
                    Dish(0, "Выберите первое"),
                    Dish(0, "Выберите первое")
                ))
                dishForChoiceCopy.put("Second", arrayListOf(
                    Dish(0, "Выберите второе"),
                    Dish(0, "Выберите второе"),
                    Dish(0, "Выберите второе")
                ))
                dishForChoiceCopy.put("Drink", arrayListOf(
                    Dish(0, "Выберите напиток"),
                    Dish(0, "Выберите напиток"),
                    Dish(0, "Выберите напиток")
                ))
                dishForChoiceLive.postValue(dishForChoiceCopy)
            }
        }

    }

    fun getListAfterChoiceLive(): MutableLiveData<HashMap<String, ArrayList<Dish>>> {
        return dishForChoiceLive
    }
    fun replaceDishForChoiceStudent(pos :Int, dish: Dish){
        dishStudentCopyList[pos]=dish
        dishStudentLive.postValue(dishStudentCopyList)
    }


    fun replaceDishForChoiceCreator(key:String,pos:Int,dish: Dish){
        dishForChoiceCopy[key]?.set(pos, dish)
        dishForChoiceLive.postValue(dishForChoiceCopy)
    }


    //ЛОГИКА ШКОЛЬНИКА

    fun upLoadStudentDish(date:String){
        database.child("ВыборШкольника/тестовый айди/$date").setValue(dishStudentLive.value)
        Log.d("QWERTY",dishStudentLive.toString())
    }
    fun downLoadMyChoice(date:String){
        Log.d("FENIX",date)
        database.child("ВыборШкольника").child("тестовый айди").child(date).get().addOnSuccessListener {
            dishStudentCopyList.clear()
            if(it.exists()){
                for (dishs in it.children){
                    val dish=dishs.getValue(Dish::class.java)
                    dishStudentCopyList.add(dish!!)
                    dishStudentLive.postValue(dishStudentCopyList)
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






    //ФУНКЦИОНАЛ АДМИНА

    //ЗАГРУЗКА ВСЕХ АККАУНТОВ

    fun downLoadAllAccounts(){
        database.child("Accounts").get().addOnSuccessListener {
            allAccountsCopy.clear()
            if(it.exists()) {
                for (accounts in it.children) {
                    val acc=accounts.getValue(AccountsData::class.java)
                    allAccountsCopy.add(acc!!)
                    allAccountsLive.postValue(allAccountsCopy)
                    Log.d("RRR", allAccountsCopy.toString())
                }
            }
        }
    }

    fun getAllAccountsCopy(): ArrayList<AccountsData> {
        return allAccountsCopy
    }

    fun createAccount(account: AccountsData){
        val sdf= SimpleDateFormat("yyyMMddHHmmss")
        val id=sdf.format(Date())
        account.id=id.toLong()
        allAccountsCopy.add(0,account)
        Firebase.database.getReference("Accounts/${account.id}").setValue(account)
    }




}