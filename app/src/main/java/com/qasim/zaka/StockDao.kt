package com.qasim.zaka

import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface StockDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertStock(stock: CompanyStock): Long // Returns row ID of the inserted item

    @Query("SELECT companyName FROM company_stock")
    fun getAllStockSymbols(): Flow<List<String>> // Real-time updates with Flow

    @Query("SELECT * FROM company_stock WHERE companyName = :name")
    fun getStockByName(name: String): Flow<CompanyStock?>

    @Delete
    fun deleteStock(stock: CompanyStock): Int // Returns number of rows deleted
}
