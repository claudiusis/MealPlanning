package com.example.mealplanning.ui.MenuCreator

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.mealplanning.R

class MenuCreatorScheduleFragment : Fragment() {

    companion object {
        fun newInstance() = MenuCreatorScheduleFragment()
    }

    private lateinit var viewModel: MenuCreatorScheduleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu_creator_schedule, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MenuCreatorScheduleViewModel::class.java)
        // TODO: Use the ViewModel
    }

}