package com.example.mealplanning.ui.MenuCreator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mealplanning.R
import com.example.mealplanning.databinding.FragmentFoodChooseMenuBinding
import com.example.mealplanning.ui.student.account.AccountViewModel

class FoodChooseMenuFragment : Fragment() {

    companion object {
        fun newInstance() = FoodChooseMenuFragment()
    }
    private var _binding: FragmentFoodChooseMenuBinding? = null
    private lateinit var viewModel: FoodChooseMenuViewModel

    private var recyclerView: RecyclerView? = null
    private lateinit var dataList: ArrayList<DataClass>
    lateinit var imageList: Array<Int>
    lateinit var titleList: Array<String>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val homeViewModel =
            ViewModelProvider(this)[AccountViewModel::class.java]

        _binding = FragmentFoodChooseMenuBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[FoodChooseMenuViewModel::class.java]

        imageList = arrayOf(
                R.drawable.a,
                R.drawable.b,
                R.drawable.c,
                R.drawable.d,
                R.drawable.e,
                R.drawable.f,
                R.drawable.g,
                R.drawable.h,
                R.drawable.i,
                R.drawable.j
            )

            titleList = arrayOf(
                "1",
                "2",
                "3",
                "4",
                "5",
                "6",
                "7",
                "8",
                "9",
                "10",
            )


            recyclerView = _binding?.recyclerView
            recyclerView?.layoutManager = LinearLayoutManager(context)
            recyclerView?.setHasFixedSize(true)

            dataList = arrayListOf<DataClass>()
            getData()

    }


    private fun getData(){
        for (i in imageList.indices){
            val dataClass = DataClass(imageList[i], titleList[i])
            dataList.add(dataClass)
        }
        recyclerView?.adapter = AdapterClass(dataList)
    }

}