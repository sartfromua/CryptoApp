package com.example.cryptoapp.presentation

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cryptoapp.LOG_TAG
import com.example.cryptoapp.UNDEFINED_CRYPTO_NAME
import com.example.cryptoapp.data.DataBaseRepository
import com.example.cryptoapp.domain.CryptoCard
import com.example.cryptoapp.domain.Usecase.EditCryptoCard
import com.example.cryptoapp.domain.Usecase.GetCryptoCard
import com.example.cryptoapp.domain.Usecase.RemoveCryptoCard
import com.example.cryptoapp.domain.retrofit.CryptoDataInfoOneCrypto
import com.example.cryptoapp.domain.retrofit.ResponseCryptoCardMapper
import com.example.cryptoapp.domain.retrofit.RetrofitCommon
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MoreInfoViewModel(application: Application): AndroidViewModel(application) {
    private val repository = DataBaseRepository(application)

    private val getCryptoCardUseCase = GetCryptoCard(repository)
    private val editCryptoCardUseCase = EditCryptoCard(repository)
    private val removeCryptoCard = RemoveCryptoCard(repository)

    private val _cardLiveData = MutableLiveData<CryptoCard>()
    val cardLiveData: LiveData<CryptoCard>
        get() = _cardLiveData

    private var _finishActivityLD = MutableLiveData<Unit>()
    val finishActivityLD: LiveData<Unit>
        get() = _finishActivityLD

    fun editCard(card: CryptoCard) {
        viewModelScope.launch {
            editCryptoCardUseCase.editCryptoCard(card)
        }
    }

    fun removeCard(card: CryptoCard?) {
        viewModelScope.launch {
            if (card != null)
                removeCryptoCard.removeCryptoCard(card)
        }
    }

    fun getCryptoCard(name: String) {
        viewModelScope.launch {
            _cardLiveData.value = getCryptoCardUseCase.getCryptoCard(name)
        }
    }

    fun updateCryptoCard() {
        viewModelScope.launch {
            try {
                Log.d(LOG_TAG, "Card in call: " + cardLiveData.value)
                val call = cardLiveData.value?.let {
                    RetrofitCommon.retrofitService
                        .getSoloCryptoInfo(_cardLiveData.value?.name ?: UNDEFINED_CRYPTO_NAME)
                }

                call?.enqueue(object : Callback<CryptoDataInfoOneCrypto> {
                    override fun onResponse(
                        call: Call<CryptoDataInfoOneCrypto>,
                        response: Response<CryptoDataInfoOneCrypto>
                    ) {
                        if (response.isSuccessful) {
                            Log.d(LOG_TAG, "onResponse:")
                            Log.d(LOG_TAG, response.toString())
                            Log.d(LOG_TAG, response.body().toString())

                            val card: CryptoCard = ResponseCryptoCardMapper
                                .responseToOneCard(response = response.body()!!,
                                    _cardLiveData.value?.name ?: UNDEFINED_CRYPTO_NAME,
                                    _cardLiveData.value?.topPlace ?: -1)


                            editCard(card)
                            _cardLiveData.value = card

                        } else {
                            Log.d(LOG_TAG, "Error in onResponse")
                        }
                    }

                    override fun onFailure(call: Call<CryptoDataInfoOneCrypto>, t: Throwable) {
                        Log.d(LOG_TAG, "Error in Call")
                        Log.d(LOG_TAG, call.toString())
                        Log.d(LOG_TAG, t.message.toString())
                    }
                })

                Log.d(LOG_TAG, "Call: " + call.toString())

            } catch (e: Exception) {
                Log.d(LOG_TAG, "Retrofit exception: " + e.message.toString())
            }
        }
    }

}