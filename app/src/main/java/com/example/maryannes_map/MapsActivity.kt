package com.example.maryannes_map

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem

import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.example.maryannes_map.databinding.ActivityMapsBinding

class MapsActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private lateinit var binding: ActivityMapsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMapsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        setMapLongClick(map)

        //These coordinates represent the latitude and longitude of the Googleplex
        val latitude = -1.3029856605821541
        val longitude = 36.91120096746766
        val zoomLevel = 20f

        val homeLatLng = LatLng(latitude,longitude)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(homeLatLng,zoomLevel))
        map.addMarker(MarkerOptions().position(homeLatLng))
    }
    override fun onCreateOptionsMenu(menu: Menu?):Boolean{
        val inflater=menuInflater
        inflater.inflate(R.menu.map_options,menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem)= when (item.itemId){
        //Change the map type based on the users selection
        R.id.normal_map ->{
            map.mapType=GoogleMap.MAP_TYPE_NORMAL
            true
        }
        R.id.hybrid_map->{
            map.mapType=GoogleMap.MAP_TYPE_HYBRID
            true
        }
        R.id.satellite_map-> {
            map.mapType = GoogleMap.MAP_TYPE_SATELLITE
            true
        }
        R.id.terrain_map-> {
            map.mapType = GoogleMap.MAP_TYPE_TERRAIN
            true
        }
        else->super.onOptionsItemSelected(item)

    }
    private fun setMapLongClick(map:GoogleMap){
        map.setOnMapLongClickListener{LatLng->
            map.addMarker(
                MarkerOptions()
                    .position(LatLng)
            )
        }
    }

}