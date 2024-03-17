package com.example.mealplanning.ui.student.menu


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mealplanning.R
import com.example.mealplanning.databinding.FragmentMenuStudentBinding
import com.example.mealplanning.viewModels.StudentViewModel

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuStudentBinding? = null
    private val mBinding get() = _binding!!
    private val viewModelStudent: StudentViewModel by activityViewModels<StudentViewModel>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,

        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMenuStudentBinding.inflate(inflater, container, false)
        val checkBox1=mBinding.checkFood1
        val checkBox2=mBinding.checkFood2
        val checkBox3=mBinding.checkFood3



        mBinding.foodName1.text=viewModelStudent.getDishFromChoice(0).name
        mBinding.foodName2.text=viewModelStudent.getDishFromChoice(1).name
        mBinding.foodName3.text=viewModelStudent.getDishFromChoice(2).name

        mBinding.layoutFirstDish.setOnClickListener {
            if (it !=checkBox1){
                viewModelStudent.setShowMore(0)
                findNavController().navigate(R.id.action_menuFragment_to_informationFragment)
            }
        }

        mBinding.layoutSecondDish.setOnClickListener {
            if (it !=checkBox2){
                viewModelStudent.setShowMore(1)
                findNavController().navigate(R.id.action_menuFragment_to_informationFragment)
            }
        }

        mBinding.layoutThreedDish.setOnClickListener {
            if (it !=checkBox3){
                viewModelStudent.setShowMore(2)
                findNavController().navigate(R.id.action_menuFragment_to_informationFragment)
            }
        }

//        mBinding.imageFood1.setOnClickListener {
//            val bundle = bundleOf()
//            bundle.putString("nameDish", mBinding.foodName1.text.toString())
//            findNavController().navigate(R.id.action_menuFragment_to_informationFragment, bundle)
//        }
//
//        mBinding.foodName1.setOnClickListener {
//            val bundle = bundleOf()
//            bundle.putString("nameDish", mBinding.foodName1.text.toString())
//            findNavController().navigate(R.id.action_menuFragment_to_informationFragment, bundle)
//        }
//
//        mBinding.imageFood2.setOnClickListener {
//            val bundle = bundleOf()
//            bundle.putString("nameDish", mBinding.foodName2.text.toString())
//            findNavController().navigate(R.id.action_menuFragment_to_informationFragment, bundle)
//        }
//
//
//        mBinding.foodName2.setOnClickListener {
//            val bundle = bundleOf()
//            bundle.putString("nameDish", mBinding.foodName2.text.toString())
//            findNavController().navigate(R.id.action_menuFragment_to_informationFragment, bundle)
//        }
//
//
//        mBinding.imageFood3.setOnClickListener {
//            val bundle = bundleOf()
//            bundle.putString("nameDish", mBinding.foodName3.text.toString())
//            findNavController().navigate(R.id.action_menuFragment_to_informationFragment, bundle)
//        }
//
//
//        mBinding.foodName3.setOnClickListener {
//            val bundle = bundleOf()
//            bundle.putString("nameDish", mBinding.foodName3.text.toString())
//            findNavController().navigate(R.id.action_menuFragment_to_informationFragment, bundle)
//        }





        checkBox1.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                checkBox2.isChecked=false
                checkBox3.isChecked=false
            }
        }

        checkBox2.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                checkBox1.isChecked=false
                checkBox3.isChecked=false
            }
        }


        checkBox3.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked){
                checkBox2.isChecked=false
                checkBox1.isChecked=false
            }
        }


        return mBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}