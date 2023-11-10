package com.example.walknavigator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.MapKitFactory
import com.example.walknavigator.R


class MainActivity : AppCompatActivity() {

    private lateinit var mapView: MapView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Инициализация Yandex Map Kit
        MapKitFactory.setApiKey("7761eaaf-7983-4493-8fd5-3b6d002f1627")
        MapKitFactory.initialize(this)
        setContentView(R.layout.activity_main)

        // Настройка карты
        mapView = findViewById(R.id.mapView)
        mapView.map.isRotateGesturesEnabled = true
        mapView.map.isZoomGesturesEnabled = true
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
        MapKitFactory.getInstance().onStart()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
        MapKitFactory.getInstance().onStop()
    }
}
