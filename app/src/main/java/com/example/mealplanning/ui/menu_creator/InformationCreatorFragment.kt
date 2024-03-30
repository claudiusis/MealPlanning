package com.example.mealplanning.ui.menu_creator

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.mealplanning.databinding.FragmentInformationBinding
import com.example.mealplanning.viewModels.CreatorViewModel
import com.example.mealplanning.viewModels.StudentViewModel

class InformationCreatorFragment : Fragment() {


    private var _binding: FragmentInformationBinding?=null
    private val viewModelCreator: CreatorViewModel by activityViewModels<CreatorViewModel>()
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding= FragmentInformationBinding.inflate(inflater,container,false)

        mBinding.buttonGoBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        val numberDish=viewModelCreator.getPositionChoice()

        lateinit var dish : Dish
        if(numberDish >= 100) {
            dish = viewModelCreator.getDishFromChoice(numberDish - 100)
        }
        else {
            dish = viewModelCreator.getFromAllDish(numberDish)
        }


        Log.d("QWERTY", numberDish.toString())
        mBinding.nameDish.text=dish.name
        mBinding.ingredients.text=dish.ingredients


        return mBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }





}