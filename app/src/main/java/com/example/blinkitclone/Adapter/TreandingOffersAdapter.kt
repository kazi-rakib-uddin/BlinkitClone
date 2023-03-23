package com.example.blinkitclone.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blinkitclone.R
import com.example.blinkitclone.databinding.CustomBestsellersBinding
import com.example.blinkitclone.databinding.CustomShopCatagoryBinding
import com.example.blinkitclone.databinding.CustomTrendingOffersBinding

class TreandingOffersAdapter() : RecyclerView.Adapter<TreandingOffersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = CustomTrendingOffersBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {

        return 6
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

       holder.binding.logoImage.setImageResource(R.drawable.kissam_logo)
       holder.binding.image.setImageResource(R.drawable.kissan_jam)
    }

    inner class ViewHolder(val binding: CustomTrendingOffersBinding) : RecyclerView.ViewHolder(binding.root)

}