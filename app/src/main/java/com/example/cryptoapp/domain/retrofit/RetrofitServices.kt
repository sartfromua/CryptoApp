package com.example.cryptoapp.domain.retrofit

import com.example.cryptoapp.domain.CryptoCard
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.Objects

interface RetrofitServices {

    @GET("data/top/totalvolfull")
    fun getCryptoList(@Query("limit") limit: Int,
                      @Query("tsym") toSyms: String
    ): Call<CryptoDataResponse>

    @GET("data/pricemultifull")
    fun getSoloCryptoInfo(@Query("fsyms") fromSyms: String,
                      @Query("tsyms") toSyms: String = "USD"
    ): Call<CryptoDataInfoOneCrypto>
}