package com.qasim.zaka

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.qasim.zaka.ui.theme.QasimZaka_COMP304_FinalTest_F24Theme
import kotlinx.coroutines.launch

class DisplayActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val stockName = intent.getStringExtra("stock_name") ?: ""
        val app = application as MyApplication
        val repository = StockRepository(app.database.stockDao())
        val stockViewModel = StockViewModel(repository)

        setContent {
            QasimZaka_COMP304_FinalTest_F24Theme (darkTheme = true) {
                val stockDetails = remember { mutableStateOf<CompanyStock?>(null) }
                val scope = rememberCoroutineScope()

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LaunchedEffect(stockName) {
                        repository.getStockByName(stockName).collect { stock ->
                            stockDetails.value = stock
                        }
                    }

                    DisplayScreen(
                        stock = stockDetails.value,
                        onBack = { finish() },
                        onDelete = {
                            val currentStock = stockDetails.value
                            if (currentStock != null) {
                                scope.launch {
                                    stockViewModel.deleteStock(currentStock)
                                    finish() // Return after deletion
                                }
                            }
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun DisplayScreen(stock: CompanyStock?, onBack: () -> Unit, onDelete: () -> Unit) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        stock?.let {
            Text("Company Name: ${it.companyName}")
            Text("Opening Price: ${it.openingPrice}")
            Text("Closing Price: ${it.closingPrice}")
        } ?: Text("No data found")

        Spacer(modifier = Modifier.height(16.dp))

        Row {
            Button(onClick = onBack, modifier = Modifier.weight(1f)) {
                Text("Back")
            }

            Spacer(modifier = Modifier.width(8.dp))

            Button(onClick = onDelete, modifier = Modifier.weight(1f)) {
                Text("Delete")
            }
        }
    }
}
