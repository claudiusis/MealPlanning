package com.example.mealplanning.ui.cook

import androidx.fragment.app.viewModels
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mealplanning.R

class CookFoodInfoFragment : Fragment() {

    companion object {
        fun newInstance() = CookFoodInfoFragment()
    }

    private val viewModel: CookFoodInfoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // TODO: Use the ViewModel
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_cook_food_info, container, false)
    }
}