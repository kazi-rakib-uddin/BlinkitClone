package com.example.blinkitclone.Fragment

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.NestedScrollView
import androidx.fragment.app.Fragment
import com.example.blinkitclone.MainActivity
import com.example.blinkitclone.R
import com.example.blinkitclone.databinding.FragmentSingleStoreBinding


class SingleStoreFragment : Fragment() {

    private var _binding : FragmentSingleStoreBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSingleStoreBinding.inflate(inflater,container,false)
        (requireActivity() as MainActivity).binding.toolbar.visibility = View.GONE
        (requireActivity() as MainActivity).binding.shadowView.visibility = View.GONE

        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)

        if (Build.VERSION.SDK_INT > Build.VERSION_CODES.KITKAT)
        {
            //requireActivity().window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        }

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.nesScrollView.setOnScrollChangeListener(NestedScrollView.OnScrollChangeListener {
                v: NestedScrollView, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->

            //Log.d("TAG", "Scroll Y:  $scrollY")

            if (scrollY == 1)
            {
               // requireActivity().setTheme(R.style.NoActionBar)
                Toast.makeText(requireContext(),"hi",Toast.LENGTH_SHORT).show()
                Log.d("TAG", "onViewCreated: "+"hi")
            }


            /*if (v.getChildAt(v.childCount - 1) != null) {
                if (scrollY >= v.getChildAt(v.childCount - 1)
                        .measuredHeight - v.measuredHeight &&
                    scrollY > oldScrollY
                ) {
                    //code to fetch more data for endless scrolling
                }
            }*/
        })
    }

}
