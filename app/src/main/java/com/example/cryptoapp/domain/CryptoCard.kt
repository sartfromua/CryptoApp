package com.example.cryptoapp.domain

class CryptoCard (
    val name: String,
    val priceUSD: Double,
    val priceUAH: Double,
    val minToday: Double,
    val maxToday: Double,
    val priceChange: Double
){
    override fun toString(): String {
        return "$name: %10lf".format(priceUSD)
    }
}