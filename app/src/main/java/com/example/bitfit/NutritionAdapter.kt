package com.example.bitfit

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class NutritionAdapter(private var ListOfNutritionInfo: List<NutritionData>) :
    RecyclerView.Adapter<NutritionAdapter.NutritionViewHolder>() {

    inner class NutritionViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val foodTextView = itemView.findViewById<TextView>(R.id.foodIdTv)
        val caloriesTextView = itemView.findViewById<TextView>(R.id.caloriesAmountTV)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NutritionViewHolder {
        val context = parent.context
        val inflater = LayoutInflater.from(context)
        val listView = inflater.inflate(R.layout.nutrition_calories_entries, parent, false)
        return NutritionViewHolder(listView)
    }

    // Involves populating data into the item through holder
    override fun onBindViewHolder(holder: NutritionViewHolder, position: Int) {
        // Get the data model based on position
        val pos: NutritionData = ListOfNutritionInfo.get(position)
        // Set item views based on your views and data model
        val foodTypeDisplay = holder.foodTextView
        foodTypeDisplay.text = pos.foodType
        val caloriesDisplay = holder.caloriesTextView
        caloriesDisplay.text = pos.caloriesCount.toString()
    }

    override fun getItemCount(): Int {
        return ListOfNutritionInfo.size
    }
}