package com.example.blinkitclone.Fragment

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.viewpager.widget.ViewPager.OnPageChangeListener
import com.example.blinkitclone.Adapter.*
import com.example.blinkitclone.MainActivity
import com.example.blinkitclone.R
import com.example.blinkitclone.databinding.CustomSingleProductDialogBinding
import com.example.blinkitclone.databinding.FragmentSubCategoryBinding
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import java.util.*


class SubCategoryFragment : Fragment() {

    private var _binding : FragmentSubCategoryBinding? = null
    val binding get() = _binding!!

    private var _binding_bsd : CustomSingleProductDialogBinding? = null
    val binding_bsd get() = _binding_bsd!!

    private lateinit var subCategoryAdapter: SubCategoryAdapter
    private lateinit var subCategoryItemAdapter: SubCategoryItemAdapter
    private lateinit var multipleBannerAdapter: MultipleBannerAdapter
    private lateinit var singleProductSliderAdapter: SingleProductSliderAdapter
    private lateinit var bestsellersAdapter: BestsellersAdapter

    private var multipleBannerArrayList = listOf(
        "https://trivenisupermarket.com/img/triveni-indian-supermarket-coming-soon.jpg",
        "https://marcado.in/images/ffff.jpg",
        "https://trivenisupermarket.com/img/what-can-you-get.jpeg"

    )

    private var singleProductArrayList = listOf(

        R.drawable.img_amul_milk,
        R.drawable.img_amul_milk,
        R.drawable.img_amul_milk,
        R.drawable.img_amul_milk,
        R.drawable.img_amul_milk,

    )

    private var currentPage = 0
    private val DELAY_MS : Long = 500
    private val PERIOD_MS  : Long = 3000


    private lateinit var mDots: Array<TextView>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSubCategoryBinding.inflate(inflater,container,false)
        (requireActivity() as MainActivity).binding.toolbar.visibility = View.VISIBLE
        (requireActivity() as MainActivity).binding.shadowView.visibility = View.VISIBLE

        (requireActivity() as MainActivity).binding.toolbar.title = "Cold Drinks & Juices"
        (requireActivity() as MainActivity).binding.toolbar.navigationIcon =
            ContextCompat.getDrawable(requireContext(), R.drawable.ic_arrow_back_24)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (requireActivity() as MainActivity).binding.toolbar.setNavigationOnClickListener {

            findNavController().popBackStack()
        }


        subCategoryAdapter = SubCategoryAdapter(requireContext())
        subCategoryItemAdapter = SubCategoryItemAdapter(::bottonDialog)
        multipleBannerAdapter = MultipleBannerAdapter(requireContext(),multipleBannerArrayList)

        binding.rvSubCategory.adapter = subCategoryAdapter
        binding.rvSubCategoryItem.adapter = subCategoryItemAdapter
        binding.pagerSlider.adapter = multipleBannerAdapter
        binding.indicator.setViewPager(binding.pagerSlider)


        val mHandler = Handler()
        var update = object : Runnable {
            override fun run() {

                if (currentPage == multipleBannerArrayList.size) {
                    currentPage = 0;
                }

                binding.pagerSlider.setCurrentItem(currentPage++, true)
            }
        }


        Timer().schedule(object : TimerTask() {
            override fun run() {
                mHandler.post(update)
            }
        }, DELAY_MS, PERIOD_MS)


        val listener: OnPageChangeListener = object : OnPageChangeListener {
            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {

                currentPage = position
            }

            override fun onPageScrollStateChanged(state: Int) {}
        }

        binding.pagerSlider.addOnPageChangeListener(listener)




    }



    private fun bottonDialog()
    {

        singleProductSliderAdapter = SingleProductSliderAdapter(requireContext(),singleProductArrayList)
        bestsellersAdapter = BestsellersAdapter()

        val dialog = BottomSheetDialog(requireContext(), R.style.TransparentBottomSheetDialogTheme)
        _binding_bsd = CustomSingleProductDialogBinding.inflate(LayoutInflater.from(requireContext()))

        //binding_bsd.textView.text = "There are many variations of passages of Lorem Ipsum available, but the majority have suffered alteration in some form, by injected humour, or randomised words which don't look even slightly believable."
        //makeTextViewResizable(binding_bsd.textView, 3, "view more details", true, binding_bsd.textView.text.toString());
        binding_bsd.pagerSlider.adapter =singleProductSliderAdapter
        binding_bsd.rvSimilarProduct.adapter =bestsellersAdapter
        binding_bsd.rvLike.adapter =bestsellersAdapter

        binding_bsd.btnViewMore.setOnClickListener {

            /*binding_bsd.linMoreDetails.visibility = View.VISIBLE

            val params: LinearLayout.LayoutParams =
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)

            val params1: LinearLayout.LayoutParams =
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)

            binding_bsd.btnViewLess.visibility = View.VISIBLE

            params.setMargins(0, 0, 0, 0)
            params1.setMargins(10, 5, 0, 150)

            binding_bsd.btnViewMore.layoutParams = params
            //binding_bsd.linMoreDetails.layoutParams = params1
            binding_bsd.btnViewLess.layoutParams = params1

            binding_bsd.btnViewMore.visibility = View.GONE
            binding_bsd.btnViewLess.visibility = View.VISIBLE*/


        }

        binding_bsd.btnViewLess.setOnClickListener {

            binding_bsd.linMoreDetails.visibility = View.GONE
            binding_bsd.btnViewMore.visibility = View.VISIBLE
            binding_bsd.btnViewLess.visibility = View.GONE

            val params: LinearLayout.LayoutParams =
                LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)


            params.setMargins(10, 5, 0, 50)

            binding_bsd.btnViewMore.layoutParams = params


        }





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
