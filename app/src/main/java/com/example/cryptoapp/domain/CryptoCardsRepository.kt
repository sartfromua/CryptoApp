package com.example.cryptoapp.domain

import androidx.lifecycle.LiveData

interface CryptoCardsRepository {
    suspend fun addCryptoCard(cryptoCard: CryptoCard)
    suspend fun getCryptoCard(name: String): CryptoCard
    fun getCryptoCardsList(): LiveData<List<CryptoCard>>
    suspend fun editCryptoCard(cryptoCard: CryptoCard)
    suspend fun removeCryptoCard(cryptoCard: CryptoCard)
}