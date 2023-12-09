package com.example.cryptoapp.domain

data class CurrencyNames(
    val namesList: MutableList<String> = emptyList<String>().toMutableList()
) {
}
