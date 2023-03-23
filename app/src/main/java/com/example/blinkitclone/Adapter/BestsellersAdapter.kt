package com.example.blinkitclone.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blinkitclone.databinding.CustomBestsellersBinding

class BestsellersAdapter() : RecyclerView.Adapter<BestsellersAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = CustomBestsellersBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {

        return 6
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

       holder.binding.IncDecText.text ="hi"
    }

    inner class ViewHolder(val binding: CustomBestsellersBinding) : RecyclerView.ViewHolder(binding.root)

}