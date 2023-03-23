package com.example.blinkitclone.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blinkitclone.databinding.CustomBestsellersBinding
import com.example.blinkitclone.databinding.CustomShopCatagoryBinding

class ShopCategoryAdapter() : RecyclerView.Adapter<ShopCategoryAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = CustomShopCatagoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {

        return 8
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

       holder.binding.name.text ="Grocery & Staple"
    }

    inner class ViewHolder(val binding: CustomShopCatagoryBinding) : RecyclerView.ViewHolder(binding.root)

}