package com.example.bitfit

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch


class NutritionFragment : Fragment() {

    private val savedNutritionData = mutableListOf<NutritionData>()
    private lateinit var logRecyclerView: RecyclerView
    private lateinit var logAdapter: NutritionAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Change this statement to store the view in a variable instead of a return statement
        val view = inflater.inflate(R.layout.fragment_log, container, false)

        // Add these configurations for the recyclerView and to configure the adapter
        val layoutManager = LinearLayoutManager(context)
        logRecyclerView = view.findViewById(R.id.listRV)
        logRecyclerView.layoutManager = layoutManager
        logRecyclerView.setHasFixedSize(true)
        logAdapter = NutritionAdapter(savedNutritionData)
        logRecyclerView.adapter = logAdapter


        lifecycleScope.launch {
            (activity?.application as NutritionApplication).db.nutritionDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    NutritionData(entity.typeOfFood, entity.amountOfCalories)
                }.also { mappedList ->
                    savedNutritionData.clear()
                    savedNutritionData.addAll(mappedList)
                    logAdapter.notifyDataSetChanged()
                }
            }
        }
        // Update the return statement to return the inflated view from above
        return view
    }

    companion object {
        @JvmStatic
        fun newInstance(): NutritionFragment{
            return NutritionFragment()
        }
    }
}