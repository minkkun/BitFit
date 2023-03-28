package com.example.bitfit
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "nutrition_table")
data class NutritionEntity(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    @ColumnInfo(name = "food") val typeOfFood: String?,
    @ColumnInfo(name = "calories") val amountOfCalories: String?
)



