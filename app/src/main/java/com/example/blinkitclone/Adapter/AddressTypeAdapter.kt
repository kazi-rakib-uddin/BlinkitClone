package com.example.blinkitclone.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blinkitclone.R
import com.example.blinkitclone.databinding.CustomAddressBinding
import com.example.blinkitclone.databinding.CustomAddressTypeBinding
import com.example.blinkitclone.databinding.CustomBestsellersBinding
import com.example.blinkitclone.databinding.CustomShopCatagoryBinding
import com.example.blinkitclone.databinding.CustomTrendingOffersBinding

class AddressTypeAdapter() : RecyclerView.Adapter<AddressTypeAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = CustomAddressTypeBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {

        return 3
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

      holder.binding.txtType.text = "Home"
    }

    inner class ViewHolder(val binding: CustomAddressTypeBinding) : RecyclerView.ViewHolder(binding.root)

}