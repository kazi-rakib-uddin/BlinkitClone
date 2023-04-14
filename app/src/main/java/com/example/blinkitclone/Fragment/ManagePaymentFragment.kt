package com.example.blinkitclone.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.navigation.fragment.findNavController
import com.example.blinkitclone.MainActivity
import com.example.blinkitclone.R
import com.example.blinkitclone.databinding.FragmentManagePaymentBinding

class ManagePaymentFragment : Fragment() {

    private var _binding : FragmentManagePaymentBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentManagePaymentBinding.inflate(inflater,container,false)

        (requireActivity() as MainActivity).binding.toolbar.visibility = View.VISIBLE
        (requireActivity() as MainActivity).binding.shadowView.visibility = View.VISIBLE

        (requireActivity() as MainActivity).binding.toolbar.title = "Manage Payment Methods"
        (requireActivity() as MainActivity).binding.toolbar.navigationIcon =
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back_24)

        (requireActivity() as MainActivity).binding.viewCart.visibility = View.GONE

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.btnUpiExpand.setOnClickListener {

            if (binding.linUpiExpand.isVisible)
            {
                binding.linUpiExpand.visibility = View.GONE
                binding.btnUpiExpand.setImageResource(R.drawable.ic_down)
            }
            else
            {
                binding.linUpiExpand.visibility = View.VISIBLE
                binding.btnUpiExpand.setImageResource(R.drawable.ic_up)
            }
        }



        binding.btnPaytmExpand.setOnClickListener {

            if (binding.linPaytmExpand.isVisible)
            {
                binding.linPaytmExpand.visibility = View.GONE
                binding.btnPaytmExpand.setImageResource(R.drawable.ic_down)
            }
            else
            {
                binding.linPaytmExpand.visibility = View.VISIBLE
                binding.btnPaytmExpand.setImageResource(R.drawable.ic_up)
            }
        }


        binding.btnMobikwikExpand.setOnClickListener {

            if (binding.linMobikwikExpand.isVisible)
            {
                binding.linMobikwikExpand.visibility = View.GONE
                binding.btnMobikwikExpand.setImageResource(R.drawable.ic_down)
            }
            else
            {
                binding.linMobikwikExpand.visibility = View.VISIBLE
                binding.btnMobikwikExpand.setImageResource(R.drawable.ic_up)
            }
        }



        (requireActivity() as MainActivity).binding.toolbar.setNavigationOnClickListener {

            findNavController().popBackStack()
        }
    }

}