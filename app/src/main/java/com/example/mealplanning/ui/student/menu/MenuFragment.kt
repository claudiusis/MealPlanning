package com.example.mealplanning.ui.student.menu


import android.annotation.SuppressLint
import android.icu.util.Calendar
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mealplanning.R
import com.example.mealplanning.databinding.FragmentMenuStudentBinding
import com.example.mealplanning.viewModels.StudentViewModel

class MenuFragment : Fragment() {

    private var _binding: FragmentMenuStudentBinding? = null
    private val mBinding get() = _binding!!
    private val viewModelStudent: StudentViewModel by activityViewModels<StudentViewModel>()

    private lateinit var studentDishAdapter: AdapterStudentDish


    @SuppressLint("NotifyDataSetChanged")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,

        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentMenuStudentBinding.inflate(inflater, container, false)
        val checkBox1=mBinding.checkFood1
        val checkBox2=mBinding.checkFood2
        val checkBox3=mBinding.checkFood3



//        mBinding.foodName1.text=viewModelStudent.getDishFromChoice(0).name
//        mBinding.foodName2.text=viewModelStudent.getDishFromChoice(1).name
//        mBinding.foodName3.text=viewModelStudent.getDishFromChoice(2).name

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



        //Переделка под ресайкл
        val calendar= Calendar.getInstance()
        val calendarView=mBinding.calendarView
        val dateChoiceString=viewModelStudent.getDateCalendar()
        val calendarChoice = Calendar.getInstance()
        calendar.set(dateChoiceString.substringAfter("m").substringBefore("y").toInt(),
            dateChoiceString.substringAfter("d").substringBefore("m").toInt() - 1,
            dateChoiceString.substringBefore("d").toInt())
        val selectedDateInMillis = calendar.timeInMillis
        calendarView.setDate(selectedDateInMillis, true, true)
        calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
            val selectedDate = "${dayOfMonth}d${month + 1}m${year}y"
            viewModelStudent.setDateCalendar(selectedDate)
//            viewModelStudent.downLoadMyChoice()
            viewModelStudent.downLoadDishForChoice()
        }

        studentDishAdapter= AdapterStudentDish(this,viewModelStudent)
        mBinding.recyclerStudentChoice.layoutManager=LinearLayoutManager(requireContext())
        viewModelStudent.getStudentDishLive().observe(
            viewLifecycleOwner,
        ){
            array->studentDishAdapter.notesList=array
            studentDishAdapter.notifyDataSetChanged()
            Log.d("FENIX","${studentDishAdapter.notesList}Массив в адаптере")
        }
        mBinding.recyclerStudentChoice.adapter=studentDishAdapter



        return mBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}