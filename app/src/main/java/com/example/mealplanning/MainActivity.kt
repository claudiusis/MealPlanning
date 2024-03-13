package com.example.mealplanning

import android.app.DatePickerDialog
import android.media.Image
import android.os.Bundle
import android.view.Menu
import android.view.View
import android.widget.Button
import android.widget.CalendarView
import android.widget.ImageButton
import android.widget.TextView
import android.widget.Toast
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mealplanning.databinding.ActivityMainBinding
import com.example.mealplanning.ui.start.StartFragment
import com.example.mealplanning.ui.student.menu.MenuFragment
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var appBarConfiguration : AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if(supportFragmentManager.findFragmentById(R.id.nav_host_fragment_activity_main)!=null){
            binding.navView.visibility = View.GONE
        }

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment_activity_main)
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.navigation_menu_student, R.id.navigation_analytics_student, R.id.navigation_account_student
            )
        )
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)

        val btnStudent = findViewById<Button>(R.id.studentBtn)

        btnStudent.setOnClickListener {
            navController.navigate(R.id.navigation_menu_student)
            binding.navView.visibility=View.VISIBLE
        }

        val btnMenuCreator = findViewById<Button>(R.id.workerBtn)

        btnMenuCreator.setOnClickListener {
            navController.navigate(R.id.menuCreatorScheduleFragment)
            binding.navView.visibility=View.VISIBLE
        }




    }

   /* private fun getData(){
        for (i in imageList.indices){
            val dataClass = DataClass(imageList[i], titleList[i])
            dataList.add(dataClass)
        }
        recyclerView.adapter = AdapterClass(dataList)
    }*/


}