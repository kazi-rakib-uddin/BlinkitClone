package com.example.blinkitclone.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blinkitclone.databinding.CustomAddToCartBinding
import com.example.blinkitclone.databinding.CustomBestsellersBinding

class CartAdapter() : RecyclerView.Adapter<CartAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = CustomAddToCartBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {

        return 2
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

       holder.binding.txtPrice.text ="₹27"
    }

    inner class ViewHolder(val binding: CustomAddToCartBinding) : RecyclerView.ViewHolder(binding.root)

}