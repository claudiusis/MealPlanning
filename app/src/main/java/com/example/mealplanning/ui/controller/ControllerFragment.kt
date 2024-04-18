package com.example.mealplanning.ui.controller

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mealplanning.R
import com.example.mealplanning.databinding.FragmentControlerBinding
import com.example.mealplanning.viewModels.ControllerViewModel


class ControllerFragment : Fragment() {

    private var _binding: FragmentControlerBinding? = null
    private val viewModelController:ControllerViewModel by activityViewModels<ControllerViewModel>()
    private lateinit var productRecycler : RecyclerView
    private lateinit var adapter : ControllerProductsAdapter

    private val mBinding get()=_binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentControlerBinding.inflate(layoutInflater)
        return mBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mBinding.createSupply.setOnClickListener {
            findNavController().navigate(R.id.action_controllerFragment_to_productCreationFragment)
        }

        adapter= ControllerProductsAdapter()
        mBinding.productRecycler.layoutManager=LinearLayoutManager(requireContext())
        viewModelController.getAllProductsLive().observe(
            viewLifecycleOwner,
        ){
            arr->
            adapter.productList = arr

        }
        mBinding.productRecycler.adapter=adapter

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }


}