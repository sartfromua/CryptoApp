package com.example.cryptoapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.cryptoapp.data.CryptoCardRepositoryImpl
import com.example.cryptoapp.domain.CryptoCard
import com.example.cryptoapp.domain.Usecase.GetCryptoCardsList

class MainViewModel: ViewModel() {
    private val repository = CryptoCardRepositoryImpl
    private val getCryptoCardsListUseCase = GetCryptoCardsList(repository)

    val livedata: LiveData<List<CryptoCard>>
        get() = getCryptoCardsListUseCase.getCryptoCardsList()

}