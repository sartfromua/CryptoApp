package com.example.cryptoapp.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.data.DataBaseRepository
import com.example.cryptoapp.domain.CryptoCard
import com.example.cryptoapp.domain.CurrencyNames
import com.example.cryptoapp.domain.Usecase.AddCryptoCard
import com.example.cryptoapp.domain.Usecase.GetCryptoCardsList
import com.example.cryptoapp.domain.Usecase.GetCurrencyNamesList
import com.example.cryptoapp.domain.Usecase.RemoveCryptoCard
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository = DataBaseRepository(application)
    private val getCryptoCardsListUseCase = GetCryptoCardsList(repository)
    private val removeCryptoCardUseCase = RemoveCryptoCard(repository)
    private val addCryptoCardUseCase = AddCryptoCard(repository)
    private val getCurrencyNamesListUseCase = GetCurrencyNamesList(repository)

    val livedata: LiveData<List<CryptoCard>>
        get() = getCryptoCardsListUseCase.getCryptoCardsList()

    fun removeShopItem(card: CryptoCard) {
        viewModelScope.launch {
            removeCryptoCardUseCase.removeCryptoCard(card)
        }
    }

    private var _currencyNamesList = MutableLiveData<CurrencyNames>()

    val currencyNamesList: LiveData<CurrencyNames>
        get() = _currencyNamesList

    fun getCurrencyNamesList() {
        viewModelScope.launch {
            val names = getCurrencyNamesListUseCase.getCurrencyNamesList()
//            Log.d("XXXXX", names.toString())
            _currencyNamesList.value = names
        }
    }

//    suspend fun getCurrencyNamesList(): List<String> {
//        return getCurrencyNamesListUseCase.getCurrencyNamesList()
//    }

    fun addShopItem(card: CryptoCard) {
        viewModelScope.launch {
            addCryptoCardUseCase.addCryptoCard(card)
        }
    }
}