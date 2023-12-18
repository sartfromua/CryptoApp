package com.example.cryptoapp.domain.Usecase

import androidx.lifecycle.LiveData
import com.example.cryptoapp.domain.CryptoCard
import com.example.cryptoapp.domain.CryptoCardsRepository

class GetCryptoCard (
    private val repository: CryptoCardsRepository)
{
    suspend fun getCryptoCard(name: String): CryptoCard {
        return repository.getCryptoCard(name)
    }
}