package com.example.cryptoapp.domain.Usecase

import com.example.cryptoapp.domain.CryptoCard
import com.example.cryptoapp.domain.CryptoCardsRepository

class EditCryptoCard (
    private var repository: CryptoCardsRepository
) {
    fun editCryptoCard(cryptoCard: CryptoCard){
        repository.editCryptoCard(cryptoCard)
    }
}