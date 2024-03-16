package menu_creator

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mealplanning.R

class ChooseFood : Fragment() {


    private fun generateDummyList(size: Int): List<Dish> {

        val list = ArrayList<Dish>()

        for (i in 0 until size) {
            val drawable = when (i % 3) {
                0 -> R.drawable.baseline_analytics_24
                1 -> R.drawable.baseline_account_circle_24
                else -> R.drawable.baseline_menu_book_24
            }

            val item = Dish("Item $i", "Line 2", drawable)
            list += item
        }

        return list
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val exampleList = generateDummyList(20)

        val view = inflater.inflate(R.layout.fragment_choose_food, container, false)

        val recyclerView = view.findViewById<RecyclerView>(R.id.dishes_rv)
        val adapter = DishAdapter(exampleList)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(context)

        return view
    }
}