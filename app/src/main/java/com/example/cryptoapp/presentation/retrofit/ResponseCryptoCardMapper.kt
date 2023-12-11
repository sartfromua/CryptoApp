package com.example.cryptoapp.presentation.retrofit

import android.os.SystemClock
import com.example.cryptoapp.UNDEFINED
import com.example.cryptoapp.domain.CryptoCard

object ResponseCryptoCardMapper {
    fun responseToCard(cardName: String, lastUpdated: Long, response: CryptoDataResponse): CryptoCard {
        return CryptoCard(
            cardName,
            priceUSD = response.RAW[cardName]!!["USD"]?.PRICE ?: UNDEFINED,
            priceUAH = response.RAW[cardName]!!["UAH"]?.PRICE ?: UNDEFINED,
            minToday = response.RAW[cardName]!!["USD"]?.LOW24HOUR ?: UNDEFINED,
            maxToday = response.RAW[cardName]!!["USD"]?.HIGH24HOUR ?: UNDEFINED,
            SystemClock.elapsedRealtime()
        )
    }
}