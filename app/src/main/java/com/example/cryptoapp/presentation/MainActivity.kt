package com.example.cryptoapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
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
import com.example.cryptoapp.presentation.retrofit.ResponseCryptoCardMapper
import com.example.cryptoapp.presentation.retrofit.RetrofitCommon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: CryptoCardAdapter
    private lateinit var viewModel: MainViewModel

    lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
//        repoImpl()
    }

    var names: CurrencyNames = CurrencyNames()
    private fun initViews() {
        //ViewModel
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.livedata.observe(this) {
            adapter.cryptoCards = it
        }

        // RecyclerView
        adapter = CryptoCardAdapter()

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter.clickListener = { view: View, card: CryptoCard ->
            try {
                launchMoreInfoActivity(card.name)
            } catch (e: Exception) {
                Log.d(LOG_TAG, "Failed to launch MoreInfoActivity!")
            }
        }

        viewModel.currencyNamesList.observe(this) {
            names = it
        }

        binding.refreshButton.setOnClickListener {
            try {
                updateCryptoCards()
            } catch (e: Exception) {
                Log.d(LOG_TAG, "Error in updatingCryptoCards\n" + e.message)
            }
        }
        }

    private fun updateCryptoCards() {
        viewModel.getCurrencyNamesList()
//        Log.d(LOG_TAG, names.namesList.toString())
        if (names.namesList != emptyList<String>()) {
            val call = RetrofitCommon.retrofitService
                .getCryptoList(names.namesList.joinToString(","), "USD,UAH")

            call.enqueue(object : Callback<CryptoDataResponse> {
                override fun onResponse(call: Call<CryptoDataResponse>, response: Response<CryptoDataResponse>) {
                    if (response.isSuccessful) {
                        Log.d(LOG_TAG, "onResponse:")
                        Log.d(LOG_TAG, response.toString())
                        Log.d(LOG_TAG, response.body().toString())

                        Log.d(LOG_TAG, "NamesList: ${names.namesList}")
                        for (cardName in names.namesList) {

                            val card: CryptoCard =
                                ResponseCryptoCardMapper.responseToCard(
                                    cardName = cardName,
                                    lastUpdated = SystemClock.elapsedRealtimeNanos(),
                                    response = response.body()!!
                                )

                            viewModel.editCryptoCard(card)
                        }

                    } else {
                        Log.d(LOG_TAG, "Error in onResponse")
                    }
                }

                override fun onFailure(call: Call<CryptoDataResponse>, t: Throwable) {
                    Log.d(LOG_TAG, "Error in Call")
                    Log.d(LOG_TAG, t.toString())
                }
            })
            Log.d(
                LOG_TAG,
                call.toString()
            )
        }
    }

    private fun repoImpl() {
        viewModel.addCryptoCard(CryptoCard("BTC"))
        viewModel.addCryptoCard(CryptoCard("LTC"))
        viewModel.addCryptoCard(CryptoCard("ETH"))
        viewModel.addCryptoCard(
            CryptoCard("USDT", 1.03,  37.0, 36.9, 38.0, 0)
        )
    }

    private fun launchMoreInfoActivity(cryptoName: String) {
        intent = Intent(this, MoreInfoActivity::class.java)
        intent.putExtra(EXTRA_CRYPTO_NAME, cryptoName)
        startActivity(intent)
    }
}
