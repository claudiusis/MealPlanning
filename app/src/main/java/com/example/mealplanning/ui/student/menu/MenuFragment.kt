package com.example.mealplanning.ui.student.menu

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.mealplanning.R
import com.example.mealplanning.databinding.FragmentMenuStudentBinding

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuStudentBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val notificationsViewModel = ViewModelProvider(this)[MenuViewModel::class.java]

        _binding = FragmentMenuStudentBinding.inflate(inflater, container, false)
 /*       val root: View = binding.root

        val textView: TextView = binding.textNotifications
        notificationsViewModel.text.observe(viewLifecycleOwner) {
            textView.text = it
        } */

        binding.imageFood1.setOnClickListener {
            val bundle = bundleOf()
            bundle.putString("nameDish", binding.foodName1.text.toString())
            findNavController().navigate(R.id.action_navigation_menu_student_to_informationFragment, bundle)
        }

        binding.foodName1.setOnClickListener {
            val bundle = bundleOf()
            bundle.putString("nameDish", binding.foodName1.text.toString())
            findNavController().navigate(R.id.action_navigation_menu_student_to_informationFragment, bundle)
        }

        binding.imageFood2.setOnClickListener {
            val bundle = bundleOf()
            bundle.putString("nameDish", binding.foodName2.text.toString())
            findNavController().navigate(R.id.action_navigation_menu_student_to_informationFragment, bundle)
        }


        binding.foodName2.setOnClickListener {
            val bundle = bundleOf()
            bundle.putString("nameDish", binding.foodName2.text.toString())
            findNavController().navigate(R.id.action_navigation_menu_student_to_informationFragment, bundle)
        }


        binding.imageFood3.setOnClickListener {
            val bundle = bundleOf()
            bundle.putString("nameDish", binding.foodName3.text.toString())
            findNavController().navigate(R.id.action_navigation_menu_student_to_informationFragment, bundle)
        }


        binding.foodName3.setOnClickListener {
            val bundle = bundleOf()
            bundle.putString("nameDish", binding.foodName3.text.toString())
            findNavController().navigate(R.id.action_navigation_menu_student_to_informationFragment, bundle)
        }


        val checkBox1=binding.checkFood1
        val checkBox2=binding.checkFood2
        val checkBox3=binding.checkFood3


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


        return _binding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}