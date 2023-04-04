package com.example.bitfit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import kotlin.math.max
import kotlin.math.min

class DashboardFragment : Fragment() {
    private lateinit var nutritionDao: NutritionDao
    private lateinit var avgCalNumber: TextView
    private lateinit var minCalNumber: TextView
    private lateinit var maxCalNumber: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        nutritionDao = (activity?.application as NutritionApplication).db.nutritionDao()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_dashboard, container, false)

        avgCalNumber = view.findViewById<TextView>(R.id.avg_cal_num)
        maxCalNumber = view.findViewById<TextView>(R.id.max_cal_num)
        minCalNumber = view.findViewById<TextView>(R.id.min_cal_num)

        return view
    }

    override fun onResume() {
        super.onResume()
        val nutritionRecords = mutableListOf<NutritionData>()

        lifecycleScope.launch {
            nutritionDao.getAll().collect { entities ->
                nutritionRecords.addAll(entities.map {
                    NutritionData(
                        it.typeOfFood,
                        it.amountOfCalories
                    )
                })
                var totalCalories = 0
                var minCalories = Int.MAX_VALUE
                var maxCalories = Int.MIN_VALUE

                for (record in nutritionRecords) {
                    val calories = record.caloriesCount?.toIntOrNull() ?: 0
                    totalCalories += calories
                    minCalories = min(minCalories, calories)
                    maxCalories = max(maxCalories, calories)
                }

                val recordCount = nutritionRecords.size
                val average = if (recordCount > 0) totalCalories / recordCount else 0

                // Set the values of the TextViews to display the calculated values
                avgCalNumber.text = if (recordCount > 0) average.toString() else ""
                minCalNumber.text = if (minCalories != Int.MAX_VALUE) minCalories.toString() else ""
                maxCalNumber.text = if (maxCalories != Int.MIN_VALUE) maxCalories.toString() else ""
            }
        }
    }

    companion object {
        fun newInstance(): DashboardFragment {
            return DashboardFragment()
        }
    }
}
