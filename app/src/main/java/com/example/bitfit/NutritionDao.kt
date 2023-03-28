package com.example.bitfit

import androidx.room.*
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
//import com.example.bitfit.NutritionEntity as NutritionEntity1

@Dao
interface NutritionDao {
    @Query("SELECT * FROM nutrition_table")
    fun getAll(): Flow<List<NutritionEntity>>

    @Insert
    fun insertAll(meals: List<NutritionEntity>)

    @Insert
    fun insert(nutritionEntity: NutritionEntity)

    @Query("DELETE FROM nutrition_table")
    fun deleteAll()
}