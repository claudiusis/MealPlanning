package com.example.mealplanning.ui.student.account

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.example.mealplanning.databinding.FragmentAccountStudentBinding
import com.example.mealplanning.viewModels.StudentViewModel

class AccountFragment : Fragment() {

    private var _binding: FragmentAccountStudentBinding? = null

    private val viewModelStudent: StudentViewModel by activityViewModels<StudentViewModel>()
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAccountStudentBinding.inflate(inflater, container, false)

        viewModelStudent.downLoadDishForChoice()
        return mBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}