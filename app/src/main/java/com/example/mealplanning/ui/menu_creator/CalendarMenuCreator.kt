package com.example.mealplanning.ui.menu_creator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mealplanning.R
import com.example.mealplanning.databinding.FragmentCalendarMenuCreatorBinding
import com.example.mealplanning.viewModels.CreatorViewModel


class CalendarMenuCreator : Fragment() {

    private var _binding: FragmentCalendarMenuCreatorBinding?=null
    private val viewModelCreator: CreatorViewModel by activityViewModels<CreatorViewModel>()
    val recyclerView: RecyclerView = mBinding.chooseFoodRecyclerView
    val data = listOf("Блюдо 1", "Блюдо 2", "Блюдо 3")
    private val mBinding get()=_binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentCalendarMenuCreatorBinding.inflate(inflater, container, false)

        viewModelCreator.downLoadAllDish()



        val adapter = EmptyDishAdapter(data)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())




        mBinding.chooseFoodRecyclerView.setOnClickListener {
            findNavController().navigate(R.id.action_calendarMenuCreator_to_chooseFood)
        }

        mBinding.chooseFoodRecyclerView.setOnClickListener {
            findNavController().navigate(R.id.action_calendarMenuCreator_to_chooseFood)
        }

        mBinding.chooseFoodRecyclerView.setOnClickListener {
            findNavController().navigate(R.id.action_calendarMenuCreator_to_chooseFood)
        }


        mBinding.btnConfirm.setOnClickListener {
            viewModelCreator.upLoadSelectedDish()
        }


        return mBinding.root
    }



}