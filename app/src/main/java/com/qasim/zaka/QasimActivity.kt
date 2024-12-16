package com.qasim.zaka

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.qasim.zaka.ui.theme.QasimZaka_COMP304_FinalTest_F24Theme
import android.content.Intent
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.ui.unit.dp

class QasimActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QasimZaka_COMP304_FinalTest_F24Theme (darkTheme = true) {
                val app = application as MyApplication
                val repository = StockRepository(app.database.stockDao())
                val stockViewModel = StockViewModel(repository)

                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ){
                    MainScreen(stockViewModel) { stockName ->
                        val intent = Intent(this, DisplayActivity::class.java)
                        intent.putExtra("stock_name", stockName)
                        startActivity(intent)
                    }
                }
            }
        }
    }
}

@Composable
fun MainScreen(viewModel: StockViewModel, onStockClick: (String) -> Unit) {
    var companyName by remember { mutableStateOf("") }
    var openingPrice by remember { mutableStateOf("") }
    var closingPrice by remember { mutableStateOf("") }

    // Observe the stock symbols from StateFlow
    val stockSymbols by viewModel.stockSymbols.collectAsState()

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        TextField(
            value = companyName,
            onValueChange = { companyName = it },
            label = { Text("Company Name") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = openingPrice,
            onValueChange = { openingPrice = it },
            label = { Text("Opening Price") },
            modifier = Modifier.fillMaxWidth()
        )
        TextField(
            value = closingPrice,
            onValueChange = { closingPrice = it },
            label = { Text("Closing Price") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (companyName.isNotBlank() && openingPrice.isNotBlank() && closingPrice.isNotBlank()) {
                    val stock = CompanyStock(
                        companyName,
                        openingPrice.toDoubleOrNull() ?: 0.0,
                        closingPrice.toDoubleOrNull() ?: 0.0
                    )
                    viewModel.addStock(stock)
                }
            },
            modifier = Modifier.padding(vertical = 8.dp)
        ) {
            Text("Add Stock")
        }

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn {
            items(stockSymbols) { symbol ->
                Button(
                    onClick = { onStockClick(symbol) },
                    modifier = Modifier.fillMaxWidth().padding(vertical = 4.dp)
                ) {
                    Text(symbol)
                }
            }
        }
    }
}
