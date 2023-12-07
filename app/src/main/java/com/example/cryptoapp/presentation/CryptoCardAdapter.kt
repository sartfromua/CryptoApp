package com.example.cryptoapp.presentation

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.cryptoapp.R
import com.example.cryptoapp.domain.CryptoCard

class CryptoCardAdapter:
    RecyclerView.Adapter<CryptoCardAdapter.CryptoCardViewHolder>() {

    var cryptoCards = listOf<CryptoCard>()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    class CryptoCardViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.textCryptoName)
        val rate = view.findViewById<TextView>(R.id.textCryptoExchangeRate)
        val cardView = view.findViewById<CardView>(R.id.cryptoCardView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoCardViewHolder {
        val view: View = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.activity_crypto_card, parent, false)

        return CryptoCardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return cryptoCards.size
    }

    var clickListener: ((view: View, card: CryptoCard) -> Unit)? = null

    override fun onBindViewHolder(holder: CryptoCardViewHolder, position: Int) {
        val card = cryptoCards[position]
        with (holder) {
            name.text = card.name
            rate.text = card.priceUSD.toString()
            cardView.setOnClickListener { clickListener?.invoke(cardView, card) }
        }

    }

}