package com.qasim.zaka

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "company_stock")
data class CompanyStock(
    @PrimaryKey val companyName: String,
    val openingPrice: Double,
    val closingPrice: Double
)
