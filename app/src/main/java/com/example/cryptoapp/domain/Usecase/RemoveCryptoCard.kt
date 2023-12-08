package com.example.cryptoapp.domain.Usecase

import com.example.cryptoapp.domain.CryptoCard
import com.example.cryptoapp.domain.CryptoCardsRepository

class RemoveCryptoCard (
    private var repository: CryptoCardsRepository
) {
    suspend fun removeCryptoCard(cryptoCard: CryptoCard){
        repository.removeCryptoCard(cryptoCard)
    }
}