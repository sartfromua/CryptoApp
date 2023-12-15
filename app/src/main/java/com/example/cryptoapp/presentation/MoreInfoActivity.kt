package com.example.cryptoapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.cryptoapp.EXTRA_CRYPTO_NAME
import com.example.cryptoapp.LOG_TAG
import com.example.cryptoapp.R
import com.example.cryptoapp.UNDEFINED_CRYPTO_NAME
import com.example.cryptoapp.databinding.FragmentMoreInfoBinding

class MoreInfoActivity : AppCompatActivity() {

    private lateinit var viewModel: MoreInfoViewModel

     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         setContentView(R.layout.activity_more_info)

         viewModel = ViewModelProvider(this)[MoreInfoViewModel::class.java]

         parseIntent()


    }

    private fun setupFragment(fragment: MoreInfoFragment) {
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.moreInfoFragmentContainer, fragment)
            .commit()
    }



    private lateinit var cardName: String
    private fun parseIntent() {
        if (intent.hasExtra(EXTRA_CRYPTO_NAME)) {
            val cardName = intent.getStringExtra(EXTRA_CRYPTO_NAME) ?: UNDEFINED_CRYPTO_NAME
            setupFragment(MoreInfoFragment.newInstanceFragmentMoreInfo(cardName))
        } else throw IllegalArgumentException("No crypto name in intent! MoreInfoActivity!")
    }
}