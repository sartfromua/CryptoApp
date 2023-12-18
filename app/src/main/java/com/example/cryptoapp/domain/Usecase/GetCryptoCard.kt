package com.example.cryptoapp.domain.Usecase

import androidx.lifecycle.LiveData
import com.example.cryptoapp.domain.CryptoCard
import com.example.cryptoapp.domain.CryptoCardsRepository

class GetCryptoCard (
    private val repository: CryptoCardsRepository)
{
    fun getCryptoCard(name: String): LiveData<CryptoCard> {
        return repository.getCryptoCard(name)
    }
}