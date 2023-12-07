package com.example.cryptoapp.domain.Usecase

import com.example.cryptoapp.domain.CryptoCard
import com.example.cryptoapp.domain.CryptoCardsRepository

class AddCryptoCard (
    private var repository: CryptoCardsRepository) {
    fun addCryptoCard(cryptoCard: CryptoCard){
        repository.addCryptoCard(cryptoCard)
    }
}