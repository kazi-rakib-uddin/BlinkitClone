package com.example.blinkitclone.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.navigation.fragment.findNavController
import com.example.blinkitclone.Adapter.AddressAdapter
import com.example.blinkitclone.Adapter.BestsellersAdapter
import com.example.blinkitclone.Adapter.CartAdapter
import com.example.blinkitclone.Adapter.SingleProductSliderAdapter
import com.example.blinkitclone.MainActivity
import com.example.blinkitclone.R
import com.example.blinkitclone.databinding.CustomChangeAddressDialogBinding
import com.example.blinkitclone.databinding.CustomSingleProductDialogBinding
import com.example.blinkitclone.databinding.FragmentCheckoutBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog

class CheckoutFragment : Fragment() {

    private var _binding : FragmentCheckoutBinding? = null
    val binding get() = _binding!!

    private var _binding_bsd : CustomChangeAddressDialogBinding? = null
    val binding_bsd get() = _binding_bsd!!

    private lateinit var cartAdapter : CartAdapter
    private lateinit var bestsellersAdapter: BestsellersAdapter
    private lateinit var addressAdapter : AddressAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentCheckoutBinding.inflate(inflater,container,false)

        (requireActivity() as MainActivity).binding.toolbar.visibility = View.VISIBLE
        (requireActivity() as MainActivity).binding.shadowView.visibility = View.VISIBLE

        (requireActivity() as MainActivity).binding.toolbar.title = "Checkout"
        (requireActivity() as MainActivity).binding.toolbar.navigationIcon =
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back_24)

        (requireActivity() as MainActivity).binding.viewCart.visibility = View.GONE

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        cartAdapter = CartAdapter()
        bestsellersAdapter = BestsellersAdapter()

        binding.rvCart.adapter = cartAdapter
        binding.rvBeforeCheckout.adapter = bestsellersAdapter

        (requireActivity() as MainActivity).binding.toolbar.setNavigationOnClickListener {

            findNavController().popBackStack()
        }


        binding.btnChooseAddress.setOnClickListener {

            bottonDialog()
        }


        binding.btnChange.setOnClickListener {

            bottonDialog()
        }

    }


    private fun bottonDialog()
    {

        addressAdapter = AddressAdapter()

        val dialog = BottomSheetDialog(requireContext(), R.style.TransparentBottomSheetDialogTheme)
        _binding_bsd = CustomChangeAddressDialogBinding.inflate(LayoutInflater.from(requireContext()))

        binding_bsd.rvAddress.adapter =addressAdapter

        binding_bsd.btnClose.setOnClickListener {

            dialog.dismiss()
        }

        dialog.setContentView(binding_bsd.root)

        //Full expanded bottom sheet dialog
        var bottomSheetBehavior = BottomSheetBehavior.from(binding_bsd.root.parent as View)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED

        dialog.show()

    }


}