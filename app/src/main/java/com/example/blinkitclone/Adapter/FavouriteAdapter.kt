package com.example.blinkitclone.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blinkitclone.R
import com.example.blinkitclone.databinding.CustomAddressBinding
import com.example.blinkitclone.databinding.CustomBestsellersBinding
import com.example.blinkitclone.databinding.CustomFavouriteBinding
import com.example.blinkitclone.databinding.CustomShopCatagoryBinding
import com.example.blinkitclone.databinding.CustomTrendingOffersBinding

class FavouriteAdapter() : RecyclerView.Adapter<FavouriteAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = CustomFavouriteBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {

        return 6
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

      holder.binding.originalPrice.text = "₹120"
    }

    inner class ViewHolder(val binding: CustomFavouriteBinding) : RecyclerView.ViewHolder(binding.root)

}