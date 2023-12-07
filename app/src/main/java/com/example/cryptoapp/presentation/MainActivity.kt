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

    }

    private fun launchMoreInfoActivity(cryptoName: String) {
        intent = Intent(this, MoreInfoActivity::class.java)
        intent.putExtra(EXTRA_CRYPTO_NAME, cryptoName)
        startActivity(intent)
    }
}