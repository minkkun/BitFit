package com.example.bitfit

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private val savedNutritionData = mutableListOf<NutritionData>()
    private lateinit var recy: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        Add food button
        val addNewFood = findViewById<Button>(R.id.addNewFoodBtn)
        addNewFood.setOnClickListener {
            val intent: Intent = Intent(this@MainActivity, SecondActivity::class.java)
            startActivity(intent)
        }


        val fragmentManager: FragmentManager = supportFragmentManager

        // define your fragments here
        val nutritionFragment: Fragment = NutritionFragment()
        val dashboardFragment: Fragment = DashboardFragment()
        val bottomNavigationView: BottomNavigationView = findViewById(R.id.bottom_navigation)

        // handle navigation selection
        bottomNavigationView.setOnItemSelectedListener { item ->
            lateinit var fragment: Fragment
            when (item.itemId) {
                R.id.dashboard_nav -> fragment = dashboardFragment
                R.id.log_nav -> fragment = nutritionFragment
            }
            replaceFragment(fragment)
            true
        }

        // Set default selection
        bottomNavigationView.selectedItemId = R.id.log_nav
        }

    private fun replaceFragment(articleListFragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.nutrition_frame_layout, articleListFragment)
        fragmentTransaction.commit()
    }

    }


