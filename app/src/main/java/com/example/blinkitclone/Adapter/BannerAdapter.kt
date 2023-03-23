package com.example.blinkitclone.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blinkitclone.R
import com.example.blinkitclone.databinding.CustomBannerBinding
import com.example.blinkitclone.databinding.CustomBestsellersBinding

class BannerAdapter() : RecyclerView.Adapter<BannerAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = CustomBannerBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {

        return 6
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

       holder.binding.image.setBackgroundResource(R.drawable.img_makeup_banner)
    }

    inner class ViewHolder(val binding: CustomBannerBinding) : RecyclerView.ViewHolder(binding.root)

}