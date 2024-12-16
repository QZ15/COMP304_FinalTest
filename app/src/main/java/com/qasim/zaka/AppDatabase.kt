package com.qasim.zaka

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CompanyStock::class], version = 1, exportSchema = false) // Suppress warning
abstract class AppDatabase : RoomDatabase() {
    abstract fun stockDao(): StockDao
}
