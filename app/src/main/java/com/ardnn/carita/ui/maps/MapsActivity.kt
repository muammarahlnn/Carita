package com.ardnn.carita.ui.maps

import android.Manifest
import android.content.pm.PackageManager
import android.location.Location
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.ardnn.carita.CaritaApplication
import com.ardnn.carita.R
import com.ardnn.carita.data.main.repository.source.local.model.User
import com.ardnn.carita.data.main.repository.source.remote.response.StoryResponse
import com.ardnn.carita.databinding.ActivityMapsBinding
import com.ardnn.carita.ui.util.ViewModelFactory
import com.ardnn.carita.ui.util.showToast
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import javax.inject.Inject

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val viewModel: MapsViewModel by viewModels {
        viewModelFactory
    }

    private lateinit var map: GoogleMap

    private lateinit var binding: ActivityMapsBinding

    private lateinit var user: User

    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestMultiplePermissions()
    ) {  permissions ->
        when {
            permissions[Manifest.permission.ACCESS_FINE_LOCATION] ?: false -> {
                // precise location access granted
                getMyLastLocation()
            }

            permissions[Manifest.permission.ACCESS_COARSE_LOCATION] ?: false -> {
                // only approximate location access granted
                getMyLastLocation()
            }

            else -> {
                // no location access granted
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        setupInject()

        super.onCreate(savedInstanceState)
        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupBundle()
        setupMaps()
        setupViewModel()
    }

    private fun setupInject() {
        (application as CaritaApplication).applicationComponent.inject(this)
    }

    private fun setupBundle() {
        user = intent.getParcelableExtra<User>(EXTRA_USER) as User
    }

    private fun setupMaps() {
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
    }

    private fun setupViewModel() {
        viewModel.getStories(user.token.toString())
        subscribeViewModel()
    }

    private fun subscribeViewModel() {
        viewModel.stories.observe(this) { stories ->
            showStoriesLocationMarker(stories)
        }
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        getMyLastLocation()
    }

    private fun checkPermission(permission: String): Boolean =
        ContextCompat.checkSelfPermission(
            this,
            permission
        ) == PackageManager.PERMISSION_GRANTED

    private fun getMyLastLocation() {
        if (checkPermission(Manifest.permission.ACCESS_FINE_LOCATION)
            && checkPermission(Manifest.permission.ACCESS_COARSE_LOCATION)
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location: Location? ->
                if (location != null) {
                    showMyLocationMarker(location)
                } else {
                    showToast(this@MapsActivity, getString(R.string.error_message_location))
                }
            }
        } else {
            requestPermissionLauncher.launch(
                arrayOf(
                    Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_COARSE_LOCATION,
                )
            )
        }
    }

    private fun showMyLocationMarker(location: Location) {
        val currentLocation = LatLng(location.latitude, location.longitude)
        map.addMarker(
            MarkerOptions()
                .position(currentLocation)
                .title(getString(R.string.your_location))
        )
        map.animateCamera(CameraUpdateFactory.newLatLngZoom(currentLocation, 5f))
    }

    private fun showStoriesLocationMarker(storyResponseList: List<StoryResponse>) {
        storyResponseList.forEach { story ->
            if (story.latitude != null && story.longitude != null) {
                val location = LatLng(story.latitude, story.longitude)
                map.addMarker(
                    MarkerOptions()
                        .position(location)
                        .title(story.name)
                )
            }
        }
    }

    companion object {

        const val EXTRA_USER = "extra_user"
    }
}