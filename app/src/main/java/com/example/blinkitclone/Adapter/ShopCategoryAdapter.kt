package com.example.blinkitclone.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.blinkitclone.R
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

        holder.binding.root.setOnClickListener {


            it.findNavController().navigate(R.id.action_homeFragment_to_subCategoryFragment)

        }
    }

    inner class ViewHolder(val binding: CustomShopCatagoryBinding) : RecyclerView.ViewHolder(binding.root)

}