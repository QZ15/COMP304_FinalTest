package com.qasim.zaka

import android.app.Application
import androidx.room.Room

class MyApplication : Application() {
    lateinit var database: AppDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        database = Room.databaseBuilder(
            applicationContext,
            AppDatabase::class.java,
            "company_stock_db"
        ).build()
    }
}
