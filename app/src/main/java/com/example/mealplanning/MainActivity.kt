package com.example.mealplanning

import android.os.Bundle

import androidx.appcompat.app.AppCompatActivity

import androidx.navigation.ui.AppBarConfiguration

import com.example.mealplanning.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private lateinit var appBarConfiguration : AppBarConfiguration

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}