package com.example.cryptoapp.domain.retrofit

object RetrofitCommon {
    private var BASE_URL = "https://min-api.cryptocompare.com/"

    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL)
            .create(RetrofitServices::class.java)
}