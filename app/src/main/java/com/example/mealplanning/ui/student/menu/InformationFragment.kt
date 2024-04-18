package com.example.mealplanning.ui.student.menu

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mealplanning.R
import com.example.mealplanning.databinding.FragmentInformationBinding
import com.example.mealplanning.ui.menu_creator.Dish
import com.example.mealplanning.viewModels.StudentViewModel

class InformationFragment : Fragment() {

    private var _binding: FragmentInformationBinding?=null
    private val viewModelStudent: StudentViewModel by activityViewModels<StudentViewModel>()
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentInformationBinding.inflate(inflater,container,false)

        mBinding.buttonGoBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        val numberDish=viewModelStudent.getShowMore()
//        val dish=viewModelStudent.getDishFromChoice(numberDish)
//        mBinding.nameDish.text=dish.name
//        mBinding.ingredients.text=dish.ingredients


        return mBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }





}