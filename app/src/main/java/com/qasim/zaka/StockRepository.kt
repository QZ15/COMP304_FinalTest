package com.qasim.zaka

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class StockRepository(private val stockDao: StockDao) {
    fun getAllStockSymbolsFlow(): Flow<List<String>> {
        return stockDao.getAllStockSymbols()
    }

    suspend fun insertStock(stock: CompanyStock) {
        // Move work to IO if needed
        kotlinx.coroutines.withContext(kotlinx.coroutines.Dispatchers.IO) {
            stockDao.insertStock(stock)
        }
    }

    suspend fun getStockByName(name: String): Flow<CompanyStock?> {
        return stockDao.getStockByName(name)
    }

    suspend fun deleteStock(stock: CompanyStock) {
        withContext(kotlinx.coroutines.Dispatchers.IO) {
            stockDao.deleteStock(stock)
        }
    }
}
