package com.example.mealplanning.ui.student.login

import android.icu.util.Calendar
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mealplanning.R
import com.example.mealplanning.databinding.FragmentLoginBinding
import com.example.mealplanning.viewModels.AdminViewModel
import com.example.mealplanning.viewModels.ControllerViewModel
import com.example.mealplanning.viewModels.CreatorViewModel
import com.example.mealplanning.viewModels.StudentViewModel

class LoginFragment : Fragment() {

    private val viewModelCreator: CreatorViewModel by activityViewModels<CreatorViewModel>()
    private val viewModelStudent: StudentViewModel by activityViewModels<StudentViewModel>()
    private val viewModelAdmin:AdminViewModel by activityViewModels<AdminViewModel> ()
    private val viewModelController: ControllerViewModel by activityViewModels<ControllerViewModel> ()


    private var _binding:FragmentLoginBinding?=null

    private val mBinding get()=_binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentLoginBinding.inflate(inflater, container, false)
        viewModelCreator.addDish()
        val calendar=Calendar.getInstance()
        val currentDay = calendar.get(Calendar.DAY_OF_MONTH)
        val currentMonth = calendar.get(Calendar.MONTH) + 1 // Месяцы в Calendar начинаются с 0
        val currentYear = calendar.get(Calendar.YEAR)
        viewModelCreator.setDateC(currentYear, currentMonth, currentDay)
        viewModelCreator.setDateCalendar("${currentDay}d${currentMonth}m${currentYear}y")
        viewModelStudent.setDateCalendar("${currentDay}d${currentMonth}m${currentYear}y")
        viewModelStudent.downLoadMyChoice()
        viewModelCreator.downLoadDishForChoice()
        viewModelStudent.downLoadDishForChoice()
        viewModelAdmin.downLoadAllAccounts()
        viewModelCreator.downLoadAllDish()



        mBinding.studentBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }
        mBinding.workerBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_calendarMenuCreator)
        }
        mBinding.AdminBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_adminAccountsFragment)
        }

        mBinding.controlerBtn.setOnClickListener {
            viewModelController.downLoadAllProducts()
            findNavController().navigate(R.id.action_loginFragment_to_controllerFragment)
        }

        return mBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}