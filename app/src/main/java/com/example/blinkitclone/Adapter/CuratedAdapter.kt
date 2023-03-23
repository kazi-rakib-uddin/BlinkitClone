package com.example.blinkitclone.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blinkitclone.R
import com.example.blinkitclone.databinding.CustomBestsellersBinding
import com.example.blinkitclone.databinding.CustomCuratedForYouBinding
import com.example.blinkitclone.databinding.CustomShopCatagoryBinding

class CuratedAdapter() : RecyclerView.Adapter<CuratedAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = CustomCuratedForYouBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {

        return 4
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

       holder.binding.image.setImageResource(R.drawable.img_coffee)
    }

    inner class ViewHolder(val binding: CustomCuratedForYouBinding) : RecyclerView.ViewHolder(binding.root)

}