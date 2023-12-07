package com.example.cryptoapp.domain.Usecase

import androidx.lifecycle.LiveData
import com.example.cryptoapp.domain.CryptoCard
import com.example.cryptoapp.domain.CryptoCardsRepository

class GetCryptoCardsList (
    private val repository: CryptoCardsRepository) {

    fun getCryptoCardsList(): LiveData<List<CryptoCard>> {
        return repository.getCryptoCardsList()
    }
}