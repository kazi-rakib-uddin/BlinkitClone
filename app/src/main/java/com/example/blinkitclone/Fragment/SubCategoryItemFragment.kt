package com.example.blinkitclone.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.blinkitclone.Adapter.SubCategoryAdapter
import com.example.blinkitclone.Utils.IConstant
import com.example.blinkitclone.databinding.FragmentSubCategoryItemBinding

class SubCategoryItemFragment : Fragment() {

    private var _binding : FragmentSubCategoryItemBinding? = null
    private val binding get() = _binding!!
    private lateinit var subCategoryAdapter: SubCategoryAdapter
     val CHANNELS = arrayOf(
        "CUPCAKE",
        "DONUT",
        "ECLAIR",
        "GINGERBREAD",
        "HONEYCOMB",
        "ICE_CREAM_SANDWICH",
        "JELLY_BEAN",
        "KITKAT",
        "LOLLIPOP",
        "M",
        "NOUGAT"
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSubCategoryItemBinding.inflate(inflater,container,false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }


}