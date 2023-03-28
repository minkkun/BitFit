package com.example.bitfit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.DocumentsContract.Root
import android.util.Log
import android.widget.Button
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val savedNutritionData = mutableListOf<NutritionData>()
    private lateinit var recy: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        recy = findViewById(R.id.listRV)
        val nutritionAdapter = NutritionAdapter(savedNutritionData)
        recy.adapter = nutritionAdapter
        recy.layoutManager = LinearLayoutManager(this)
        val addNewFood = findViewById<Button>(R.id.addNewFoodBtn)
        addNewFood.setOnClickListener {
            val intent: Intent = Intent(this@MainActivity, SecondActivity::class.java)
            startActivity(intent)
        }

        lifecycleScope.launch {
            (application as NutritionApplication).db.nutritionDao().getAll().collect { databaseList ->
                databaseList.map { entity ->
                    NutritionData(entity.typeOfFood, entity.amountOfCalories)
                }.also { mappedList ->
                    savedNutritionData.clear()
                    savedNutritionData.addAll(mappedList)
                    nutritionAdapter.notifyDataSetChanged()
                }
            }
        }
            


        }
    }


