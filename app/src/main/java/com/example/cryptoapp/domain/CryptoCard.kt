package com.example.cryptoapp.domain

class CryptoCard (
    val name: String,
    val priceUSD: Double,
    val secondCurrencyName: String,
    val secondPrice: Double,
    val minToday: Double,
    val maxToday: Double,
    val lastUpdated: String
){
    override fun toString(): String {
        return "$name: %10lf".format(priceUSD)
    }
}