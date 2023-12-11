package com.example.cryptoapp.domain

import com.example.cryptoapp.UNDEFINED

class CryptoCard(
    val name: String = "",
    val priceUSD: Double = UNDEFINED,
    val minToday: Double = UNDEFINED,
    val maxToday: Double = UNDEFINED,
    val market: String = "",
    val lastUpdated: Long = 0,
    val imageUrl: String = ""
){

    override fun toString(): String {
        return "$name: %10f".format(priceUSD)
    }
}