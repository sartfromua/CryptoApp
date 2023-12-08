package com.example.cryptoapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptoapp.EXTRA_CRYPTO_NAME
import com.example.cryptoapp.LOG_TAG
import com.example.cryptoapp.databinding.ActivityMainBinding
import com.example.cryptoapp.domain.CryptoCard

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

        //repoImpl()
    }

    private fun repoImpl() {
//        viewModel.addShopItem(
//            CryptoCard("LTC", 5.0, "UAH", 170.0, 5.4, 4.9, "moment ago")
//        )
//        viewModel.addShopItem(
//            CryptoCard("ETH", 1900.0, "UAH", 65000.0, 6400.9, 6500.0, "10 min ago")
//        )
//        viewModel.addShopItem(
//            CryptoCard("USDT", 1.03, "UAH", 37.0, 36.9, 38.0, "1 hour ago")
//        )
    }

    private fun launchMoreInfoActivity(cryptoName: String) {
        intent = Intent(this, MoreInfoActivity::class.java)
        intent.putExtra(EXTRA_CRYPTO_NAME, cryptoName)
        startActivity(intent)
    }
}