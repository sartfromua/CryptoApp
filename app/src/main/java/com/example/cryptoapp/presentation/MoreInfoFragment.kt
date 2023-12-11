package com.example.cryptoapp.presentation

import android.annotation.SuppressLint
import android.os.Bundle
import android.os.SystemClock
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.EXTRA_CRYPTO_NAME
import com.example.cryptoapp.MEDIA_BASE_URL
import com.example.cryptoapp.UNDEFINED_CRYPTO_NAME
import com.example.cryptoapp.databinding.FragmentMoreInfoBinding
import com.squareup.picasso.Picasso
import java.time.Clock
import kotlin.time.Duration
import kotlin.time.Duration.Companion.microseconds
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.nanoseconds
import kotlin.time.Duration.Companion.seconds

class MoreInfoFragment : Fragment() {

    private var cardName: String = UNDEFINED_CRYPTO_NAME

    lateinit var binding: FragmentMoreInfoBinding
    private lateinit var viewModel: MoreInfoViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMoreInfoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        with (requireArguments()) {
            if (containsKey(EXTRA_CRYPTO_NAME)) {
                cardName = getString(EXTRA_CRYPTO_NAME) ?: UNDEFINED_CRYPTO_NAME
            } else throw IllegalArgumentException("No EXTRA_CRYPTO_NAME in arguments for fragment!")
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel = ViewModelProvider(this)[MoreInfoViewModel::class.java]

        initViews()
        setupMode()
        registerLiveData()
    }

    private fun initViews() {
        binding.buttonUpdate.setOnClickListener {

        }
    }

    private fun setupMode() {
        viewModel.getCard(cardName)
    }

    @SuppressLint("SetTextI18n")
    private fun registerLiveData() {
        with (binding) {
            viewModel.cardLiveData.observe(viewLifecycleOwner) {
                textNameView.text = it.name + "/USD"
                textMaxPriceTodayView.text = "Max price in 24 hour: \n" + it.maxToday.toString()
                textMinPriceTodayView.text = "Min price in 24 hour: \n" + it.minToday.toString()
                textPriceView.text = "Price: \n" + it.priceUSD.toString()
                textMarket.text = "Market: \n" + it.market
                textDateUpdatedView.text = "Last updated: \n" + lastUpdatedToString(it.lastUpdated)
                Picasso.get().load(MEDIA_BASE_URL+it.imageUrl).into(cryptoLogo)
            }


            viewModel.finishActivityLD.observe(viewLifecycleOwner) {
                activity?.onBackPressed()
            }
        }
    }

    private fun lastUpdatedToString(lastUpdated: Long): String {
        val seconds = (SystemClock.elapsedRealtime() - lastUpdated).milliseconds.inWholeSeconds
        return if (seconds < 60) "$seconds seconds ago"
        else if  (seconds in 60..<3600) "${seconds.seconds.inWholeMinutes} minutes ago"
        else if (seconds in 3600..<3600*24) "${seconds.seconds.inWholeHours} hours ago"
        else "${seconds.seconds.inWholeDays} days ago"
    }


    companion object {
        @JvmStatic
        fun newInstanceFragmentMoreInfo(cardName: String) =
            MoreInfoFragment().apply {
                arguments = Bundle().apply {
                    putString(EXTRA_CRYPTO_NAME, cardName)
                }
            }
    }
}