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
            viewModelStudent.downLoadMyChoice()
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

        mBinding.buttonConfirm.setOnClickListener {
            viewModelStudent.upLoadStudentDish()
        }

        return mBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}