package com.example.blinkitclone.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.blinkitclone.Adapter.AddressAdapter
import com.example.blinkitclone.MainActivity
import com.example.blinkitclone.R
import com.example.blinkitclone.databinding.FragmentAddressBinding

class AddressFragment : Fragment() {

    private var _binding : FragmentAddressBinding? = null
    val binding get() = _binding!!

    lateinit var addressAdapter: AddressAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddressBinding.inflate(inflater,container,false)
        (requireActivity() as MainActivity).binding.toolbar.visibility = View.VISIBLE
        (requireActivity() as MainActivity).binding.shadowView.visibility = View.VISIBLE

        (requireActivity() as MainActivity).binding.toolbar.title = "My Address"
        (requireActivity() as MainActivity).binding.toolbar.navigationIcon =
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back_24)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvAddress.addItemDecoration(DividerItemDecoration(requireContext(),LinearLayoutManager.VERTICAL))
        addressAdapter = AddressAdapter()
        binding.rvAddress.adapter = addressAdapter

        (requireActivity() as MainActivity).binding.toolbar.setNavigationOnClickListener {

            findNavController().popBackStack()
        }


        binding.linNewAddress.setOnClickListener {

            findNavController().navigate(R.id.action_addressFragment_to_addAddressFragment)
        }
    }


    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}