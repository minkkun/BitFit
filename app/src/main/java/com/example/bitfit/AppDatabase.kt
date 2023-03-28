package com.example.bitfit

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [NutritionEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun nutritionDao(): NutritionDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java, "Nutrition-db"
            ).build()
    }



}


/*

 Notes. Data base class has to have three things
 1. The class must be annotated with a @Database annotation that
 includes an entities array that lists all of the data entities associated with the database.
2. The class must be an abstract class that extends RoomDatabase.
3. For each DAO class that is associated with the database, the database class must
 define an abstract method that has zero arguments and returns an instance of the DAO class.*/
