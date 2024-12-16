package com.qasim.zaka

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class StockViewModel(private val repository: StockRepository) : ViewModel() {
    private val _stockSymbols = MutableStateFlow<List<String>>(emptyList())
    val stockSymbols: StateFlow<List<String>> = _stockSymbols

    init {
        // Collect Flow and update StateFlow
        viewModelScope.launch {
            repository.getAllStockSymbolsFlow().collect { symbols ->
                _stockSymbols.value = symbols
            }
        }
    }

    suspend fun getStockDetails(name: String): Flow<CompanyStock?> {
        return repository.getStockByName(name)
    }

    fun addStock(stock: CompanyStock) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertStock(stock)
        }
    }

    fun deleteStock(stock: CompanyStock) {
        viewModelScope.launch(kotlinx.coroutines.Dispatchers.IO) {
            repository.deleteStock(stock)
        }
    }

}