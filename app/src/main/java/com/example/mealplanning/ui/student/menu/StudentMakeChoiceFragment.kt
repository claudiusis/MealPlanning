package com.example.mealplanning.ui.student.menu

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealplanning.R
import com.example.mealplanning.databinding.FragmentInformationBinding
import com.example.mealplanning.databinding.FragmentStudentMakeChoiceBinding
import com.example.mealplanning.viewModels.StudentViewModel

class StudentMakeChoiceFragment : Fragment() {

    private var _binding: FragmentStudentMakeChoiceBinding?=null
    private val viewModelStudent: StudentViewModel by activityViewModels<StudentViewModel>()
    private lateinit var recyclerStudentMakeChoice:AdapterStudentMakeChoice
    private val mBinding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentStudentMakeChoiceBinding.inflate(inflater,container,false)

        recyclerStudentMakeChoice=AdapterStudentMakeChoice(this,viewModelStudent)
        mBinding.recyclerMakeChoice.layoutManager=LinearLayoutManager(requireContext())
        viewModelStudent.getDishForChoice().observe(
            viewLifecycleOwner,
        ){
            array->recyclerStudentMakeChoice.notesList=array
        }
        mBinding.recyclerMakeChoice.adapter=recyclerStudentMakeChoice





        return mBinding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}