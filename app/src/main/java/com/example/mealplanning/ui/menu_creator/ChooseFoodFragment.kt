package com.example.mealplanning.ui.menu_creator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealplanning.databinding.FragmentChooseFoodBinding
import com.example.mealplanning.viewModels.CreatorViewModel

class ChooseFoodFragment : Fragment() {

    private var _binding:FragmentChooseFoodBinding?=null
    private  val mBinding get()=_binding!!
    private val viewModelCreator: CreatorViewModel by activityViewModels<CreatorViewModel>()
    private lateinit var recyclerAdapter: DishAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentChooseFoodBinding.inflate(inflater,container,false)

        recyclerAdapter=DishAdapter(this,viewModelCreator)
        mBinding.recyclerDish.layoutManager=LinearLayoutManager(context)
        mBinding.recyclerDish.adapter=recyclerAdapter

        recyclerAdapter.notesList= viewModelCreator.getAllDishList()!!

        return mBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }

}