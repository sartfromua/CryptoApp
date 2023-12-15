package com.example.cryptoapp.domain.retrofit

data class CryptoInfo (
    var FROMSYMBOL: String? = null,
    var PRICE: Double? = null,
    var HIGH24HOUR: Double? = null,
    var LOW24HOUR: Double? = null,
    var CONVERSIONLASTUPDATE: Double? = null,
    var IMAGEURL: String? = null,
    var MARKET: String? = null,
    var CHANGEDAY: Double? = null,
    var CHANGEHOUR: Double? = null
)