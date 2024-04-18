package com.example.mealplanning.ui.controller

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.mealplanning.R
import com.example.mealplanning.databinding.FragmentControlerBinding
import com.example.mealplanning.databinding.FragmentProductCreationBinding
import com.example.mealplanning.viewModels.ControllerViewModel

class ProductCreationFragment : Fragment() {

    private var _binding : FragmentProductCreationBinding? = null
    private val viewModelController: ControllerViewModel by activityViewModels<ControllerViewModel>()

    private val mBinding get()=_binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentProductCreationBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.addProduct.setOnClickListener {
            val product=mBinding.nameEdit.text.toString()
            val count=mBinding.amountEdit.text.toString().toInt()
            viewModelController.createSupply(product,count)
            findNavController().popBackStack()
        }

    }

}