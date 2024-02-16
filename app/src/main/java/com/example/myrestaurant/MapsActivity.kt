package com.example.myrestaurant

import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions

class MapsActivity : AppCompatActivity(), OnMapReadyCallback, GoogleMap.OnMapLongClickListener,
    GoogleMap.OnMapClickListener {

    private lateinit var txtLatitud: EditText
    private lateinit var txtLongitud: EditText
    private lateinit var mMap: GoogleMap

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_maps)

        txtLatitud = findViewById(R.id.txtLatitud)
        txtLongitud = findViewById(R.id.txtLongitud)

        val mapFragment = supportFragmentManager.findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        this.mMap.setOnMapLongClickListener(this)
        this.mMap.setOnMapClickListener(this)

        val peru = LatLng(-12.059836,-76.9522251)
        mMap.addMarker(MarkerOptions().position(peru).title("Per"))
        mMap.moveCamera(CameraUpdateFactory.newLatLng(peru))
    }


    override fun onMapLongClick(p0: LatLng) {
        txtLatitud.setText("" + p0.latitude)
        txtLongitud.setText("" + p0.longitude)
    }

    override fun onMapClick(p0: LatLng) {
        txtLatitud.setText("" + p0.latitude)
        txtLongitud.setText("" + p0.longitude)
    }


}
