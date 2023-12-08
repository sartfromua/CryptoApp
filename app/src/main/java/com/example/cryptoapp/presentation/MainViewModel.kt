package com.example.cryptoapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.data.DataBaseRepository
import com.example.cryptoapp.domain.CryptoCard
import com.example.cryptoapp.domain.Usecase.AddCryptoCard
import com.example.cryptoapp.domain.Usecase.GetCryptoCardsList
import com.example.cryptoapp.domain.Usecase.RemoveCryptoCard
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository = DataBaseRepository(application)
    private val getCryptoCardsListUseCase = GetCryptoCardsList(repository)
    private val removeCryptoCardUseCase = RemoveCryptoCard(repository)
    private val addCryptoCardUseCase = AddCryptoCard(repository)

    val livedata: LiveData<List<CryptoCard>>
        get() = getCryptoCardsListUseCase.getCryptoCardsList()

    fun removeShopItem(card: CryptoCard) {
        viewModelScope.launch {
            removeCryptoCardUseCase.removeCryptoCard(card)
        }
    }

    fun addShopItem(card: CryptoCard) {
        viewModelScope.launch {
            addCryptoCardUseCase.addCryptoCard(card)
        }
    }
}