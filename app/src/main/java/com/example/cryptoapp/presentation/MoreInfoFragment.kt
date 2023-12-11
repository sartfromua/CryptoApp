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
import com.example.cryptoapp.UNDEFINED_CRYPTO_NAME
import com.example.cryptoapp.databinding.FragmentMoreInfoBinding
import java.time.Clock
import kotlin.time.Duration
import kotlin.time.Duration.Companion.microseconds
import kotlin.time.Duration.Companion.milliseconds
import kotlin.time.Duration.Companion.nanoseconds

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

            //updateURL()
        }
    }

    private fun setupMode() {
        viewModel.getCard(cardName)
    }

//    private fun getCurrencyList(): List<String> {
//
//    }

    @SuppressLint("SetTextI18n")
    private fun registerLiveData() {
        with (binding) {
            viewModel.cardLiveData.observe(viewLifecycleOwner) {
                textNameView.text = it.name
                textMaxPriceTodayView.text = it.maxToday.toString()
                textMinPriceTodayView.text = it.minToday.toString()
                textPriceChangeView.text = it.priceUAH.toString()
                textPriceView.text = it.priceUSD.toString()
                textDateUpdatedView.text = (SystemClock.elapsedRealtime() - it.lastUpdated).milliseconds.inWholeSeconds.toString() +
                        " seconds ago"
            }

            viewModel.finishActivityLD.observe(viewLifecycleOwner) {
                activity?.onBackPressed()
            }
        }
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