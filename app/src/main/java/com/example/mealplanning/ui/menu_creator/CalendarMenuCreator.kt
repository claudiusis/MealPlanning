package com.example.mealplanning.ui.menu_creator

import android.os.Bundle
import android.util.Log
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
    private lateinit var dishAfterChoiceRecyclerAdapter:AdapterDishAfterChoice
    private val mBinding get()=_binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentCalendarMenuCreatorBinding.inflate(inflater, container, false)
        viewModelCreator.downLoadAllDish()
        viewModelCreator.setSelectedDish(viewModelCreator.getListAfterChoice())


        dishAfterChoiceRecyclerAdapter= AdapterDishAfterChoice(this,viewModelCreator)
        mBinding.chooseFoodRecyclerView.layoutManager=LinearLayoutManager(requireContext())
        mBinding.chooseFoodRecyclerView.adapter=dishAfterChoiceRecyclerAdapter
        dishAfterChoiceRecyclerAdapter.notesList=viewModelCreator.getSelectedDish()
        Log.d("FENIX","${dishAfterChoiceRecyclerAdapter.notesList}")





        mBinding.btnConfirm.setOnClickListener {
            viewModelCreator.upLoadSelectedDish()
        }


        return mBinding.root
    }



}