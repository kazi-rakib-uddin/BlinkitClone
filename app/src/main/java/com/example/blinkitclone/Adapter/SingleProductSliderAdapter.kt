package com.example.blinkitclone.Adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.example.blinkitclone.R
import java.util.*

class SingleProductSliderAdapter(val context: Context, val arrayList: List<Int>) : PagerAdapter() {

    private lateinit var layoutInflater: LayoutInflater

    override fun isViewFromObject(view: View, `object`: Any): Boolean {

        return view == `object` as ConstraintLayout
    }

    @SuppressLint("MissingInflatedId")
    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val mLayoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val itemview : View = mLayoutInflater.inflate(R.layout.custom_single_image,container,false)

        val imageView : ImageView = itemview.findViewById(R.id.banner_image)
        Glide.with(context).load(arrayList.get(position)).into(imageView)

        // Adding the View
        Objects.requireNonNull(container).addView(itemview);

        return itemview
    }


    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        // on below line we are removing view
        container.removeView(`object` as ConstraintLayout)
    }

    override fun getCount(): Int {

        return arrayList.size
    }
}