package com.example.blinkitclone.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blinkitclone.R
import com.example.blinkitclone.databinding.CustomAddressBinding
import com.example.blinkitclone.databinding.CustomBestsellersBinding
import com.example.blinkitclone.databinding.CustomShopCatagoryBinding
import com.example.blinkitclone.databinding.CustomTrendingOffersBinding

class AddressAdapter() : RecyclerView.Adapter<AddressAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = CustomAddressBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {

        return 3
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

      holder.binding.txtAddressType.text = "Home"
    }

    inner class ViewHolder(val binding: CustomAddressBinding) : RecyclerView.ViewHolder(binding.root)

}