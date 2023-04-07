package com.example.blinkitclone.Fragment

import android.Manifest
import android.animation.IntEvaluator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.Context.LOCATION_SERVICE
import android.content.Intent
import android.content.IntentSender
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.GradientDrawable
import android.location.Location
import android.location.LocationManager
import android.os.Bundle
import android.os.Looper
import android.provider.Settings
import android.view.*
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.fragment.app.Fragment
import com.example.blinkitclone.Adapter.AddressTypeAdapter
import com.example.blinkitclone.databinding.CustomFullAddressBsdBinding
import com.example.blinkitclone.databinding.FragmentAddAddressBinding
import com.google.android.gms.common.api.ResolvableApiException
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.*
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.example.blinkitclone.R


class AddAddressFragment : Fragment(), OnMapReadyCallback {

    private var _binding: FragmentAddAddressBinding? = null
    private val binding get() = _binding!!
    private val pERMISSION_ID = 42
    lateinit var mFusedLocationClient: FusedLocationProviderClient
    lateinit var mMap: GoogleMap
    var currentLocation: LatLng? = null
    private lateinit var locationManager: LocationManager
    private val REQUEST_CHECK_SETTINGS = 0x1;

    //BottomSheet Dialog
    private var _binding_bsd : CustomFullAddressBsdBinding? = null
    private val binding_bsd get() = _binding_bsd!!
    private lateinit var addressTypeAdapter : AddressTypeAdapter


    /* private val callback = OnMapReadyCallback { googleMap ->

        *//* val sydney = LatLng(-34.0, 151.0)
        googleMap.addMarker(MarkerOptions().position(sydney).title("Marker in Sydney"))
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(sydney))*//*

        getLastLocation()
    }*/

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddAddressBinding.inflate(inflater, container, false)
        // Initializing fused location client
        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireActivity())
        locationManager = requireContext().getSystemService(LOCATION_SERVICE) as LocationManager;
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val mapFragment = childFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        binding.btnGps.setOnClickListener {

            getLastLocation()
        }

        binding.btnCompleteAddress.setOnClickListener {

            addressTypeAdapter = AddressTypeAdapter()

            val dialog = BottomSheetDialog(requireContext(), R.style.TransparentBottomSheetDialogTheme)
            _binding_bsd = CustomFullAddressBsdBinding.inflate(LayoutInflater.from(requireContext()))

            binding_bsd.rvAddressType.adapter =addressTypeAdapter

            binding_bsd.btnClose.setOnClickListener {

                dialog.dismiss()
            }

            dialog.setContentView(binding_bsd.root)
            dialog.show()



        }
    }


    // Get current location
    @SuppressLint("MissingPermission")
    private fun getLastLocation() {
        if (checkPermissions()) {
            if (isLocationEnabled()) {

                mFusedLocationClient.lastLocation.addOnCompleteListener(requireActivity()) { task ->
                    val location: Location? = task.result
                    if (location == null) {
                        //requestNewLocationData()
                    } else {
                        currentLocation = LatLng(location.latitude, location.longitude)
                        mMap.clear()
                        mMap.addMarker(
                            MarkerOptions().position(currentLocation!!)
                                .title("Your location")
                                .snippet("Current Location")
                                .icon(BitmapDescriptorFactory.fromResource(R.drawable.custom_markar))

                        )
                        mMap.animateCamera(
                            CameraUpdateFactory.newLatLngZoom(
                                currentLocation!!,
                                18F
                            )
                        )

                       /* var circleOption = CircleOptions()
                            .center(currentLocation!!)
                            .radius(50.0)
                            .strokeColor(Color.TRANSPARENT)
                            .fillColor(R.color.purple_200)
                            .strokeWidth(5f)

                        val busStopCircle = mMap.addCircle(circleOption)

                        var valueAnimator = ValueAnimator()
                        valueAnimator.repeatCount = ValueAnimator.INFINITE
                        valueAnimator.repeatMode = ValueAnimator.RESTART
                        valueAnimator.setIntValues(0, 100)
                        valueAnimator.setDuration(3000)
                        valueAnimator.setEvaluator(IntEvaluator())
                        valueAnimator.interpolator = AccelerateDecelerateInterpolator()
                        valueAnimator.addUpdateListener {
                            var animatedFraction = valueAnimator.getAnimatedFraction();
                            busStopCircle.setRadius((animatedFraction * 100).toDouble())
                        }
                        valueAnimator.start();*/


                        var d = GradientDrawable()
                        d.shape = GradientDrawable.OVAL
                        d.setSize(100, 100)
                        d.setColor(0x555751FF)
                        d.setStroke(5,Color.TRANSPARENT)

                        var bitmap = Bitmap.createBitmap(d.intrinsicWidth,d.intrinsicHeight,Bitmap.Config.ARGB_8888)
                        var canvas = Canvas(bitmap)
                        d.setBounds(0,0,canvas.width, canvas.height)
                        d.draw(canvas)

                        var circle = mMap.addGroundOverlay(GroundOverlayOptions()
                            .position(currentLocation!!,1000F)
                            .image(BitmapDescriptorFactory.fromBitmap(bitmap))
                        )

                        var valueAnimator = ValueAnimator()
                        valueAnimator.repeatCount = ValueAnimator.INFINITE
                        valueAnimator.repeatMode = ValueAnimator.RESTART
                        valueAnimator.setIntValues(0, 100)
                        valueAnimator.setDuration(3000)
                        valueAnimator.setEvaluator(IntEvaluator())
                        valueAnimator.interpolator = AccelerateDecelerateInterpolator()
                        valueAnimator.addUpdateListener {
                            var animatedFraction = valueAnimator.getAnimatedFraction();
                            circle!!.setDimensions(animatedFraction * 40 * 2);
                        }
                        valueAnimator.start()


                        mMap.setOnCameraIdleListener {
                            val midLatLng: LatLng = mMap.getCameraPosition().target
                            mMap.clear()
                            binding.imgCenterMarker.visibility = View.GONE
                            mMap.addMarker(
                                MarkerOptions().position(midLatLng!!)
                                    .title("Your location")
                                    .snippet("Current Location")
                                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.custom_markar))

                            )



                            var d = GradientDrawable()
                            d.shape = GradientDrawable.OVAL
                            d.setSize(100, 100)
                            d.setColor(0x555751FF)
                            d.setStroke(5,Color.TRANSPARENT)

                            var bitmap = Bitmap.createBitmap(d.intrinsicWidth,d.intrinsicHeight,Bitmap.Config.ARGB_8888)
                            var canvas = Canvas(bitmap)
                            d.setBounds(0,0,canvas.width, canvas.height)
                            d.draw(canvas)

                            var circle = mMap.addGroundOverlay(GroundOverlayOptions()
                                .position(midLatLng!!,1000F)
                                .image(BitmapDescriptorFactory.fromBitmap(bitmap))
                            )

                            var valueAnimator = ValueAnimator()
                            valueAnimator.repeatCount = ValueAnimator.INFINITE
                            valueAnimator.repeatMode = ValueAnimator.RESTART
                            valueAnimator.setIntValues(0, 100)
                            valueAnimator.setDuration(3000)
                            valueAnimator.setEvaluator(IntEvaluator())
                            valueAnimator.interpolator = AccelerateDecelerateInterpolator()
                            valueAnimator.addUpdateListener {
                                var animatedFraction = valueAnimator.getAnimatedFraction();
                                circle!!.setDimensions(animatedFraction * 40 * 2);
                            }
                            valueAnimator.start()


                        }


                        mMap.setOnCameraMoveListener {

                            mMap.clear()
                            binding.imgCenterMarker.visibility = View.VISIBLE
                        }


                    }
                }
            } else {
                Toast.makeText(requireContext(), "Turn on location", Toast.LENGTH_LONG).show()
                val intent = Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                startActivity(intent)
            }
        } else {
            requestPermissions()
        }
    }


    // Get current location, if shifted
    // from previous location
    @SuppressLint("MissingPermission")
    private fun requestNewLocationData() {
        val mLocationRequest = LocationRequest()
        mLocationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        mLocationRequest.interval = 0
        mLocationRequest.fastestInterval = 0
        mLocationRequest.numUpdates = 1

        mFusedLocationClient = LocationServices.getFusedLocationProviderClient(requireContext())
        mFusedLocationClient.requestLocationUpdates(
            mLocationRequest, mLocationCallback,
            Looper.myLooper()
        )
    }


    // If current location could not be located, use last location
    private val mLocationCallback = object : LocationCallback() {
        override fun onLocationResult(locationResult: LocationResult) {
            val mLastLocation: Location = locationResult.lastLocation!!
            currentLocation = LatLng(mLastLocation.latitude, mLastLocation.longitude)
        }
    }

    // function to check if GPS is on
    private fun isLocationEnabled(): Boolean {
        val locationManager: LocationManager =
            requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        return locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) || locationManager.isProviderEnabled(
            LocationManager.NETWORK_PROVIDER
        )
    }


    // Check if location permissions are
    // granted to the application
    private fun checkPermissions(): Boolean {
        if (ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED &&
            ActivityCompat.checkSelfPermission(
                requireActivity(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            return true
        }
        return false
    }

    // Request permissions if not granted before
    private fun requestPermissions() {
        ActivityCompat.requestPermissions(
            requireActivity(),
            arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            pERMISSION_ID
        )
    }

    // What must happen when permission is granted
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        if (requestCode == pERMISSION_ID) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                getLastLocation()
            }
        }
    }

    override fun onMapReady(p0: GoogleMap) {

        mMap = p0
        if (ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(
                requireContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {

            return
        }
        mMap.isMyLocationEnabled = true

        if (locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)) {

            getLastLocation()



        } else {

            enableGPS()
        }
    }



    fun enableGPS() {

        activity?.let {
            val locationRequest = LocationRequest.create()
            locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY

            val builder = LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest)

            val task = LocationServices.getSettingsClient(it)
                .checkLocationSettings(builder.build())

            task.addOnSuccessListener { response ->
                val states = response.locationSettingsStates
                if (states!!.isLocationPresent) {
                    //Do something
                    getLastLocation()
                }
            }
            task.addOnFailureListener { e ->
                if (e is ResolvableApiException) {
                    try {
                        // Handle result in onActivityResult()
                        e.startResolutionForResult(
                            it,
                            REQUEST_CHECK_SETTINGS
                        )
                    } catch (sendEx: IntentSender.SendIntentException) {
                    }
                }
            }
        }


    }


}