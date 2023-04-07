package com.example.blinkitclone.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.blinkitclone.R
import com.example.blinkitclone.databinding.CustomSubCategoryBinding


class SubCategoryAdapter(
    val requireContext: Context

) :
    RecyclerView.Adapter<SubCategoryAdapter.ViewHolder>() {


    var selectedPosition: Int = -1


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val binding = CustomSubCategoryBinding.inflate(LayoutInflater.from(parent.context),parent,false)

        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {

        return 10
    }

    @SuppressLint("SuspiciousIndentation")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

      holder.binding.txtName.text = "Soft Drinks"


        if (selectedPosition == position)
        {
            holder.binding.tabLayout.setBackgroundColor(ContextCompat.getColor(requireContext, R.color.green))
        }
        else
        {
            holder.binding.tabLayout.setBackgroundColor(Color.WHITE)

        }

        holder.binding.root.setOnClickListener {

            selectedPosition = position
            holder.binding.tabLayout.setBackgroundColor(Color.GREEN)
            notifyDataSetChanged()

        }





    }

    inner class ViewHolder(val binding: CustomSubCategoryBinding) : RecyclerView.ViewHolder(binding.root)





}