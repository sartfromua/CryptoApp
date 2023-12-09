package com.example.cryptoapp.presentation.retrofit

import retrofit2.http.GET

object RetrofitCommon {
    private var BASE_URL = "https://min-api.cryptocompare.com/data/"

    val retrofitService: RetrofitServices
        get() = RetrofitClient.getClient(BASE_URL)
            .create(RetrofitServices::class.java)
}