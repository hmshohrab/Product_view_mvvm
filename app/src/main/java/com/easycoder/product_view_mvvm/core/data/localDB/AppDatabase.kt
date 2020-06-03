package com.easycoder.product_view_mvvm.core.data.localDB

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.easycoder.product_view_mvvm.BuildConfig


abstract class AppDatabase : RoomDatabase() {


    companion object {
        private val DB_NAME = BuildConfig.DB_NAME

        private var instance: AppDatabase? = null

        @Synchronized
        fun getInstance(context: Context): AppDatabase? {
            if (instance == null)
                instance =
                    create(
                        context.applicationContext
                    )

            return instance
        }

        private fun create(context: Context): AppDatabase {
            return Room.databaseBuilder(
                context, AppDatabase::class.java,
                DB_NAME
            )
                .allowMainThreadQueries()
                .fallbackToDestructiveMigration()
                .build()
        }
    }
}