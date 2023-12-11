package com.example.cryptoapp.presentation

import android.app.Application
import android.os.SystemClock
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.LOG_TAG
import com.example.cryptoapp.data.DataBaseRepository
import com.example.cryptoapp.domain.CryptoCard
import com.example.cryptoapp.domain.Usecase.AddCryptoCard
import com.example.cryptoapp.domain.Usecase.EditCryptoCard
import com.example.cryptoapp.domain.Usecase.GetCryptoCardsList
import com.example.cryptoapp.domain.Usecase.GetCurrencyNamesList
import com.example.cryptoapp.domain.Usecase.RemoveCryptoCard
import com.example.cryptoapp.domain.retrofit.CryptoDataResponse
import com.example.cryptoapp.domain.retrofit.ResponseCryptoCardMapper
import com.example.cryptoapp.domain.retrofit.RetrofitCommon
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repository = DataBaseRepository(application)
    private val getCryptoCardsListUseCase = GetCryptoCardsList(repository)
    private val removeCryptoCardUseCase = RemoveCryptoCard(repository)
    private val addCryptoCardUseCase = AddCryptoCard(repository)
    private val getCurrencyNamesListUseCase = GetCurrencyNamesList(repository)
    private val editCryptoCardUseCase = EditCryptoCard(repository)


    val livedata: LiveData<List<CryptoCard>>
        get() = getCryptoCardsListUseCase.getCryptoCardsList()

    fun removeShopItem(card: CryptoCard) {
        viewModelScope.launch {
            removeCryptoCardUseCase.removeCryptoCard(card)
        }
    }

    fun editCryptoCard(card: CryptoCard) {
        viewModelScope.launch {
            editCryptoCardUseCase.editCryptoCard(card)
        }
    }

    fun addCryptoCard(card: CryptoCard) {
        viewModelScope.launch {
            addCryptoCardUseCase.addCryptoCard(card)
        }
    }

    fun updateCryptoCards() {
        viewModelScope.launch {
            val names = getCurrencyNamesListUseCase.getCurrencyNamesList()
            Log.d(LOG_TAG, names.namesList.toString())
            try {
                val call = RetrofitCommon.retrofitService
                    .getCryptoList(25, "USD")

                call.enqueue(object : Callback<CryptoDataResponse> {
                    override fun onResponse(
                        call: Call<CryptoDataResponse>,
                        response: Response<CryptoDataResponse>
                    ) {
                        if (response.isSuccessful) {
                            Log.d(LOG_TAG, "onResponse:")
                            Log.d(LOG_TAG, response.toString())
                            Log.d(LOG_TAG, response.body().toString())

                            Log.d(LOG_TAG, "NamesList: ${names.namesList}")
                            val cards: List<CryptoCard> = ResponseCryptoCardMapper
                                .responseToCards(response = response.body()!!)
                            for (card in cards)
                                if (card.name in names.namesList)
                                    editCryptoCard(card)
                                else
                                    addCryptoCard(card)

                        } else {
                            Log.d(LOG_TAG, "Error in onResponse")
                        }
                    }

                    override fun onFailure(call: Call<CryptoDataResponse>, t: Throwable) {
                        Log.d(LOG_TAG, "Error in Call")
                        Log.d(LOG_TAG, call.toString())
                        Log.d(LOG_TAG, t.message.toString())
                    }
                })
                Log.d(LOG_TAG, call.toString())

            } catch (e: Exception) {
                Log.d(LOG_TAG, e.message.toString())
            }
        }
    }
}