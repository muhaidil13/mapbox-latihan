package com.example.mapbox_tutor

import android.graphics.BitmapFactory
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.navigateUp
import androidx.navigation.ui.setupActionBarWithNavController
import android.view.Menu
import android.view.MenuItem
import com.example.mapbox_tutor.databinding.ActivityMainBinding
import com.mapbox.geojson.Point
import com.mapbox.maps.CameraOptions
import com.mapbox.maps.MapView
import com.mapbox.maps.Style
import com.mapbox.maps.plugin.annotation.annotations
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationManager
import com.mapbox.maps.plugin.annotation.generated.PointAnnotationOptions
import com.mapbox.maps.plugin.annotation.generated.createPointAnnotationManager
import com.mapbox.maps.plugin.gestures.addOnMapClickListener

class MainActivity : AppCompatActivity() {

    private lateinit var mapView: MapView
    private lateinit var pointAnnotationManager: PointAnnotationManager

    override fun onCreate(savedInstanceState: Bundle?) {

        setContentView(R.layout.activity_main)

        mapView = findViewById(R.id.mapView)

        mapView.mapboxMap.loadStyleUri(Style.STANDARD){ style ->
            style.addImage(
                "default_marker",
                BitmapFactory.decodeResource(resources, R.drawable.red_marker)
            )
            pointAnnotationManager = mapView.annotations.createPointAnnotationManager()

        }
        mapView.mapboxMap.addOnMapClickListener { point ->
            addMarker(point.latitude(), point.longitude())
            true
        }

        super.onCreate(savedInstanceState)


    }
    private fun addMarker(latitude: Double, longitude: Double) {
        val pointAnnotationOptions: PointAnnotationOptions = PointAnnotationOptions()
            .withPoint(com.mapbox.geojson.Point.fromLngLat(longitude, latitude))
            .withIconImage("default_marker") // Ikon default, bisa diubah sesuai kebutuhan

        pointAnnotationManager.create(pointAnnotationOptions)
    }

}