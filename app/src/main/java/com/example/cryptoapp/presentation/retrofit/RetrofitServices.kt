package com.example.cryptoapp.presentation.retrofit

import com.example.cryptoapp.domain.CryptoCard
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.Objects

interface RetrofitServices {

    @GET("pricemultifull")
        fun getCryptoList(@Query("fsyms", encoded = true) fromSyms: String,
                          @Query("tsyms", encoded = true) toSyms: String
        ): Call<CryptoDataResponse>
}