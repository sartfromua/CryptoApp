package com.example.cryptoapp.domain.Usecase

import com.example.cryptoapp.domain.CryptoCard
import com.example.cryptoapp.domain.CryptoCardsRepository

class GetCryptoCard (
    private val repository: CryptoCardsRepository)
{
    fun getCryptoCard(name: String): CryptoCard {
        return repository.getCryptoCard(name)
    }
}