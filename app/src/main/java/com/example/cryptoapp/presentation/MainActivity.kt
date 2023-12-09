package com.example.cryptoapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptoapp.EXTRA_CRYPTO_NAME
import com.example.cryptoapp.LOG_TAG
import com.example.cryptoapp.databinding.ActivityMainBinding
import com.example.cryptoapp.domain.CryptoCard
import com.example.cryptoapp.domain.CurrencyNames
import com.example.cryptoapp.presentation.retrofit.CryptoDataResponse
import com.example.cryptoapp.presentation.retrofit.CryptoInfo
import com.example.cryptoapp.presentation.retrofit.RetrofitCommon
import com.google.gson.Gson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.Objects

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: CryptoCardAdapter
    private lateinit var viewModel: MainViewModel

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.livedata.observe(this) {
            adapter.cryptoCards = it

        }

        adapter = CryptoCardAdapter()

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter.clickListener = {view: View, card: CryptoCard ->
            try {
                launchMoreInfoActivity(card.name)
            } catch (e: Exception) {
                Log.d(LOG_TAG, "Failed to launch MoreInfoActivity!")
            }
        }

//        repoImpl()
        initViews()
    }

    var names: CurrencyNames = CurrencyNames()
    private fun initViews() {
        binding.refreshButton.setOnClickListener {
            viewModel.getCurrencyNamesList()
//            Log.d(LOG_TAG, names.namesList.toString())
            updateCryptoCards()
        }

        viewModel.currencyNamesList.observe(this) {
            names = it
        }


    }

    private fun updateCryptoCards() {
        if (names.namesList != emptyList<String>()) {
//            RetrofitCommon.updateURL(names.namesList, listOf("USD", "UAH"))
            val call = RetrofitCommon.retrofitService
                .getCryptoList(names.namesList.joinToString(","), "USD,UAH")

            call.enqueue(object : Callback<CryptoDataResponse> {
                override fun onResponse(call: Call<CryptoDataResponse>, response: Response<CryptoDataResponse>) {
                    if (response.isSuccessful) {
                        val cryptoList = response.body()
                        Log.d(LOG_TAG, "onResponse:")
                        Log.d(LOG_TAG, response.toString())
                        Log.d(LOG_TAG, cryptoList.toString())

                    } else {
                        Log.d(LOG_TAG, "Error in onResponse")
                    }
                }

                override fun onFailure(call: Call<CryptoDataResponse>, t: Throwable) {
                    Log.d(LOG_TAG, "Error in Call")
                    Log.d(LOG_TAG, t.toString())
                }
            })
            Log.d(LOG_TAG,
                call.toString()
            )
        }
    }

    private fun repoImpl() {
        viewModel.addShopItem(
            CryptoCard("LTC", 5.0,  170.0, 5.4, 4.9, "moment ago")
        )
        viewModel.addShopItem(
            CryptoCard("ETH", 1900.0,  65000.0, 6400.9, 6500.0, "10 min ago")
        )
        viewModel.addShopItem(
            CryptoCard("USDT", 1.03,  37.0, 36.9, 38.0, "1 hour ago")
        )
    }

    private fun launchMoreInfoActivity(cryptoName: String) {
        intent = Intent(this, MoreInfoActivity::class.java)
        intent.putExtra(EXTRA_CRYPTO_NAME, cryptoName)
        startActivity(intent)
    }
}
