package com.example.cryptoapp.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "cards_table")
data class CryptoCardEntity(
    @PrimaryKey
    @ColumnInfo(name="name_card")
    var name: String,

    @ColumnInfo(name="price_USD")
    val priceUSD: Double,

    @ColumnInfo(name="min_price_today")
    val minToday: Double,

    @ColumnInfo(name="max_price_today")
    val maxToday: Double,

    @ColumnInfo(name="market")
    val market: String,

    @ColumnInfo(name="last_updated")
    val lastUpdated: Long,

    @ColumnInfo(name="image_URL")
    val imageURL: String,

    @ColumnInfo(name="change_day")
    val changeDay: Double,

    @ColumnInfo(name="change_hour")
    val changeHour: Double,

    @ColumnInfo(name="top_place")
    val topPlace: Int

)