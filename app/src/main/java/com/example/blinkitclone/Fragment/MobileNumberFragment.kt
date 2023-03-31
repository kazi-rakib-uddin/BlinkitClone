package com.example.blinkitclone.Fragment

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.LinearInterpolator
import androidx.navigation.fragment.findNavController
import com.example.blinkitclone.MainActivity
import com.example.blinkitclone.R
import com.example.blinkitclone.databinding.FragmentMobileNumberBinding

class MobileNumberFragment : Fragment() {

    private var _binding : FragmentMobileNumberBinding? = null
    private val binding  get() = _binding!!
    //val yourActivity = activity as MainActivity
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentMobileNumberBinding.inflate(inflater,container,false)

        (requireActivity() as MainActivity).binding.toolbar.visibility = View.GONE
        (requireActivity() as MainActivity).binding.shadowView.visibility = View.GONE
        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        var valueAnimation = ValueAnimator.ofFloat(0.0f, -1.0f)
        valueAnimation.repeatCount = ValueAnimator.INFINITE
        valueAnimation.interpolator = LinearInterpolator()
        valueAnimation.duration = 10000L

        valueAnimation.addUpdateListener {

            val progress : Float = valueAnimation.getAnimatedValue() as Float
            val width : Float = binding.img.width.toFloat()
            val translationX = width * progress
            binding.img.translationX = translationX
        }
        // valueAnimation.start()

        binding.btnContinue.setOnClickListener {

            findNavController().navigate(R.id.action_mobileNumberFragment_to_homeFragment)
        }

    }
}