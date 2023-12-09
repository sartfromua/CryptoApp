package com.example.cryptoapp.domain.Usecase

import androidx.lifecycle.LiveData
import com.example.cryptoapp.domain.CryptoCardsRepository
import com.example.cryptoapp.domain.CurrencyNames

class GetCurrencyNamesList(
    private val repository: CryptoCardsRepository
) {
    suspend fun getCurrencyNamesList(): CurrencyNames {
        return repository.getCurrencyNamesList()
    }
}