package com.example.cryptoapp.presentation.retrofit

data class CryptoInfo (
    var PRICE: Double? = null,
    var HIGH24HOUR: Double? = null,
    var LOW24HOUR: Double? = null,
    var CONVERSIONLASTUPDATE: Double? = null
)