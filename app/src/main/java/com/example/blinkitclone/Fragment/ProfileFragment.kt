package com.example.blinkitclone.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.blinkitclone.MainActivity
import com.example.blinkitclone.R
import com.example.blinkitclone.databinding.FragmentProfileBinding

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        /*binding.toolbar.title = "Profile"
        binding.toolbar.navigationIcon = ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back_24)*/
        (requireActivity() as MainActivity).binding.toolbar.visibility = View.VISIBLE
        (requireActivity() as MainActivity).binding.shadowView.visibility = View.VISIBLE

        (requireActivity() as MainActivity).binding.toolbar.title = "Profile"
        (requireActivity() as MainActivity).binding.toolbar.navigationIcon =
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back_24)

        (requireActivity() as MainActivity).binding.viewCart.visibility = View.GONE

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnAddress.setOnClickListener {

            findNavController().navigate(R.id.action_profileFragment_to_addressFragment)
        }

        binding.btnFavourite.setOnClickListener {

            findNavController().navigate(R.id.action_profileFragment_to_favouriteFragment)
        }

        binding.btnPayment.setOnClickListener {

            findNavController().navigate(R.id.action_profileFragment_to_managePaymentFragment)
        }


        (requireActivity() as MainActivity).binding.toolbar.setNavigationOnClickListener {

            findNavController().popBackStack()
        }

    }

}