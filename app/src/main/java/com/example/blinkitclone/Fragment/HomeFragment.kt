package com.example.blinkitclone.Fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import com.example.blinkitclone.Adapter.*
import com.example.blinkitclone.MainActivity
import com.example.blinkitclone.Model.MultipleBannerModel
import com.example.blinkitclone.R
import com.example.blinkitclone.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var bestsellersAdapter: BestsellersAdapter
    private lateinit var shopCategoryAdapter: ShopCategoryAdapter
    private lateinit var bannerAdapter: BannerAdapter
    private lateinit var multipleBannerAdapter: MultipleBannerAdapter
    private var multipleBannerArrayList = listOf(
        "https://trivenisupermarket.com/img/triveni-indian-supermarket-coming-soon.jpg",
        "https://marcado.in/images/ffff.jpg",
        "https://trivenisupermarket.com/img/what-can-you-get.jpeg"

    )

    private lateinit var curatedAdapter: CuratedAdapter
    private lateinit var customTreandingOffersAdapter: TreandingOffersAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater,container,false)

        (requireActivity() as MainActivity).binding.toolbar.visibility = View.GONE
        (requireActivity() as MainActivity).binding.shadowView.visibility = View.GONE

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bestsellersAdapter = BestsellersAdapter()
        shopCategoryAdapter = ShopCategoryAdapter()
        bannerAdapter = BannerAdapter()
        multipleBannerAdapter = MultipleBannerAdapter(requireContext(),multipleBannerArrayList)
        curatedAdapter = CuratedAdapter()
        customTreandingOffersAdapter = TreandingOffersAdapter()


        binding.rvBestsellers.adapter = bestsellersAdapter
        binding.rvShopByCategory.adapter = shopCategoryAdapter
        binding.rvBanner.adapter = bannerAdapter
        binding.rvNewYourStore.adapter = bestsellersAdapter
        binding.rvMakeup.adapter = bestsellersAdapter
        binding.pagerSlider.adapter = multipleBannerAdapter
        binding.indicator.setViewPager(binding.pagerSlider)
        binding.rvCuratedForYou.adapter = curatedAdapter
        binding.rvFeelTheFreshness.adapter = bestsellersAdapter
        binding.rvTrendingOffers.adapter = customTreandingOffersAdapter

        binding.txtSearch.isSelected = true

        binding.btnProfile.setOnClickListener {

            findNavController().navigate(R.id.action_homeFragment_to_profileFragment)
        }

    }

}