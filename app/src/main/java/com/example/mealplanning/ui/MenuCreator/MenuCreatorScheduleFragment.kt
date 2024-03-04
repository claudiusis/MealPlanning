package com.example.mealplanning.ui.MenuCreator

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.mealplanning.R
import com.example.mealplanning.databinding.FragmentMenuCreatorScheduleBinding
import com.example.mealplanning.databinding.FragmentMenuStudentBinding
import com.example.mealplanning.ui.student.menu.MenuViewModel

class MenuCreatorScheduleFragment : Fragment() {
    private var _binding: FragmentMenuCreatorScheduleBinding? = null
    private val binding get() = _binding!!



    companion object {
        fun newInstance() = MenuCreatorScheduleFragment()
    }

    private lateinit var viewModel: MenuCreatorScheduleViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val notificationsViewModel = ViewModelProvider(this)[MenuViewModel::class.java]

        _binding = FragmentMenuCreatorScheduleBinding.inflate(inflater, container, false)

        binding.imageButtonFood1.setOnClickListener {
            findNavController().navigate(R.id.action_menuCreatorScheduleFragment_to_foodChooseMenuFragment)
        }

        return _binding!!.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MenuCreatorScheduleViewModel::class.java)
        // TODO: Use the ViewModel
    }

}