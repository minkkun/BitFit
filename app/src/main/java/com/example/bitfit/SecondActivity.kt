package com.example.bitfit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.secondactivity)

        val addButton = findViewById<Button>(R.id.RecordFoodBtn)
        val foodInput = findViewById<EditText>(R.id.foodInputET)
        val caloriesInput = findViewById<EditText>(R.id.caloriesInputET)

        addButton.setOnClickListener {
//            val food = foodInput.text.toString()
//            val calories = caloriesInput.text.toString()

//            Log.e("2nd", food)
//            Log.e("2nd", calories)
            val uid: Long = 0
            lifecycleScope.launch(IO) {
//                val nutritionData = NutritionEntity(uid, food, calories)
                (application as NutritionApplication).db.nutritionDao().insert(
//                    nutritionData
                    NutritionEntity(
                        typeOfFood = foodInput.text.toString(),
                        amountOfCalories = caloriesInput.text.toString()
                    )
                )
            }

            val myIntent: Intent = Intent(this@SecondActivity, MainActivity::class.java)
//            myIntent.putExtra("foodType", food)
//            myIntent.putExtra("amountOfCalories", calories)
            startActivity(myIntent)
        }
    }
}
