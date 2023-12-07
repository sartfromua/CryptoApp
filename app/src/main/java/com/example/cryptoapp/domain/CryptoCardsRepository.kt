package com.example.cryptoapp.domain

import androidx.lifecycle.LiveData

interface CryptoCardsRepository {
    fun addCryptoCard(cryptoCard: CryptoCard)
    fun getCryptoCard(name: String): CryptoCard
    fun getCryptoCardsList(): LiveData<List<CryptoCard>>
    fun editCryptoCard(cryptoCard: CryptoCard)
    fun removeCryptoCard(cryptoCard: CryptoCard)
}