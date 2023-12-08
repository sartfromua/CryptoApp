package com.example.cryptoapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cards_table")
data class CryptoCardEntity (
    @PrimaryKey
    @ColumnInfo(name="name_card")
    var name: String,

    @ColumnInfo(name="price_USD")
    val priceUSD: Double,

    @ColumnInfo(name="second_currency_name")
    val secondCurrencyName: String,

    @ColumnInfo(name="price_second")
    val secondPrice: Double,

    @ColumnInfo(name="min_price_today")
    val minToday: Double,

    @ColumnInfo(name="max_price_today")
    val maxToday: Double,

    @ColumnInfo(name="last_updated")
    val lastUpdated: String

)