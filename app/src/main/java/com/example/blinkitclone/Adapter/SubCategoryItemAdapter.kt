package com.example.blinkitclone.Adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.blinkitclone.databinding.CustomSubCategoryItemBinding

class SubCategoryItemAdapter(val bottonDialog: () -> Unit) : RecyclerView.Adapter<SubCategoryItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = CustomSubCategoryItemBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {

        return 10
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

      holder.binding.discountedPrice.text = "$320"

        holder.binding.imgProduct.setOnClickListener {

            bottonDialog()
        }

    }

    inner class ViewHolder(val binding: CustomSubCategoryItemBinding) : RecyclerView.ViewHolder(binding.root)

}