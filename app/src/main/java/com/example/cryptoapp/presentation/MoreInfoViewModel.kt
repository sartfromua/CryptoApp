package com.example.cryptoapp.presentation

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.cryptoapp.data.CryptoCardRepositoryImpl
import com.example.cryptoapp.domain.CryptoCard
import com.example.cryptoapp.domain.Usecase.EditCryptoCard
import com.example.cryptoapp.domain.Usecase.GetCryptoCard

class MoreInfoViewModel(application: Application): AndroidViewModel(application) {
    private val repository = CryptoCardRepositoryImpl

    private val getCryptoCardUseCase = GetCryptoCard(repository)
    private val editCryptoCard = EditCryptoCard(repository)

    private val _cardLiveData = MutableLiveData<CryptoCard>()
    val cardLiveData: LiveData<CryptoCard>
        get() = _cardLiveData

    private var _finishActivityLD = MutableLiveData<Unit>()
    val finishActivityLD: LiveData<Unit>
        get() = _finishActivityLD



    fun getCard(name: String) {
        _cardLiveData.value = getCryptoCardUseCase.getCryptoCard(name)
    }

}