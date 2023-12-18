package com.example.cryptoapp.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.view.View
import android.widget.ImageView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cryptoapp.EXTRA_CRYPTO_NAME
import com.example.cryptoapp.LOG_TAG
import com.example.cryptoapp.R
import com.example.cryptoapp.databinding.ActivityMainBinding
import com.example.cryptoapp.domain.CryptoCard
import com.example.cryptoapp.domain.CurrencyNames
import com.example.cryptoapp.domain.retrofit.CryptoDataResponse
import com.example.cryptoapp.domain.retrofit.ResponseCryptoCardMapper
import com.example.cryptoapp.domain.retrofit.RetrofitCommon
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    private lateinit var adapter: CryptoCardAdapter
    private lateinit var viewModel: MainViewModel

    lateinit var binding: ActivityMainBinding
    val isPortrait: Boolean
        get() = binding.fragmentContainerLandscape == null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViews()
//        repoImpl()
    }

    private fun initViews() {
        //ViewModel
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        viewModel.livedata.observe(this) {
            adapter.cryptoCards = it.sortedBy { it.topPlace }
            Log.d(LOG_TAG, it.sortedBy { it.topPlace }.toString())
        }

        // RecyclerView
        adapter = CryptoCardAdapter()

        binding.recyclerView.adapter = adapter
        binding.recyclerView.layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        adapter.clickListener = { view: View, card: CryptoCard ->
            try {
                if (isPortrait)
                    launchMoreInfoActivity(card.name)
                else {
                    setupFragment(MoreInfoFragment.newInstanceFragmentMoreInfo(card.name, !isPortrait))
                }
            } catch (e: Exception) {
                Log.d(LOG_TAG, "Failed to launch MoreInfoActivity!")
            }
        }

        // RefreshButton with retrofit
        binding.refreshButton.setOnClickListener {
            try {
                Log.d(LOG_TAG, "Refresh_Button")
                viewModel.updateCryptoCards()
            } catch (e: Exception) {
                Log.d(LOG_TAG, "Error in updatingCryptoCards\n" + e.message)
            }
        }
    }

    private fun setupFragment(fragment: MoreInfoFragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragmentContainerLandscape, fragment)
            .commit()
    }

    private fun repoImpl() {
        viewModel.addCryptoCard(CryptoCard("BTC"))
        viewModel.addCryptoCard(CryptoCard("LTC"))
        viewModel.addCryptoCard(CryptoCard("ETH"))
        viewModel.addCryptoCard(
            CryptoCard("USDT")
        )
    }

    private fun launchMoreInfoActivity(cryptoName: String) {
        intent = Intent(this, MoreInfoActivity::class.java)
        intent.putExtra(EXTRA_CRYPTO_NAME, cryptoName)
        startActivity(intent)
    }
}
