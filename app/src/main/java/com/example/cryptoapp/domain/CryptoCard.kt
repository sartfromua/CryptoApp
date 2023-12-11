package com.example.cryptoapp.domain

import com.example.cryptoapp.UNDEFINED

class CryptoCard(
    val name: String = "",
    val priceUSD: Double = UNDEFINED,
    val priceUAH: Double = UNDEFINED,
    val minToday: Double = UNDEFINED,
    val maxToday: Double = UNDEFINED,
    val lastUpdated: Long = 0
){

    override fun toString(): String {
        return "$name: %10f".format(priceUSD)
    }
}