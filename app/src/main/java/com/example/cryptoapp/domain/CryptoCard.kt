package com.example.cryptoapp.domain

import com.example.cryptoapp.UNDEFINED

class CryptoCard(
    val name: String = "",
    val priceUSD: Double = UNDEFINED,
    val minToday: Double = UNDEFINED,
    val maxToday: Double = UNDEFINED,
    val market: String = "",
    val lastUpdated: Long = 0,
    val imageUrl: String = "",
    val changeDay: Double = UNDEFINED,
    val changeHour: Double = UNDEFINED,
    val topPlace: Int = -1
){

    override fun toString(): String {
        return "$name: %d".format(lastUpdated)
    }
}