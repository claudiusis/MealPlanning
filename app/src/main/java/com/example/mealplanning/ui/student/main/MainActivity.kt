package com.example.mealplanning.ui.student.main

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import androidx.navigation.ui.AppBarConfiguration

import com.example.mealplanning.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}