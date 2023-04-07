package com.example.blinkitclone.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.blinkitclone.Adapter.FavouriteAdapter
import com.example.blinkitclone.MainActivity
import com.example.blinkitclone.R
import com.example.blinkitclone.databinding.FragmentFavouriteBinding


class FavouriteFragment : Fragment() {

    private var _binding : FragmentFavouriteBinding? = null
    private val binding get() = _binding!!
    private lateinit var customFavouriteAdapter: FavouriteAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFavouriteBinding.inflate(layoutInflater,container,false)
        (requireActivity() as MainActivity).binding.toolbar.visibility = View.VISIBLE
        (requireActivity() as MainActivity).binding.shadowView.visibility = View.VISIBLE

        (requireActivity() as MainActivity).binding.toolbar.title = "Your favourite items"
        (requireActivity() as MainActivity).binding.toolbar.navigationIcon =
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back_24)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        customFavouriteAdapter = FavouriteAdapter()
        binding.rvFavourite.adapter = customFavouriteAdapter

        (requireActivity() as MainActivity).binding.toolbar.setNavigationOnClickListener {

            findNavController().popBackStack()
        }
    }

}