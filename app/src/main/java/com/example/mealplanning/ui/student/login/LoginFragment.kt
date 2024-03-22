package com.example.mealplanning.ui.student.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mealplanning.R
import com.example.mealplanning.databinding.FragmentLoginBinding
import com.example.mealplanning.viewModels.CreatorViewModel

class LoginFragment : Fragment() {

    private val viewModelCreator: CreatorViewModel by activityViewModels<CreatorViewModel>()
    private var _binding:FragmentLoginBinding?=null

    private val mBinding get()=_binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentLoginBinding.inflate(inflater, container, false)

        viewModelCreator.addDish()
        viewModelCreator.downLoadDishForChoice()


        mBinding.studentBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_mainFragment)
        }
        mBinding.workerBtn.setOnClickListener {
            findNavController().navigate(R.id.action_loginFragment_to_calendarMenuCreator)
        }


        return mBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}