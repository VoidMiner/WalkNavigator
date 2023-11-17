package com.example.walknavigator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.walknavigator.databinding.ActivityMainBinding
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.mapview.MapView
import com.example.walknavigator.R



class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var mapView: MapView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Инициализация Yandex Map Kit
        MapKitFactory.setApiKey(BuildConfig.MAPKIT_API_KEY)
        MapKitFactory.initialize(this)

        binding = ActivityMainBinding.inflate(layoutInflater)
        val rootView = binding.root
        setContentView(rootView)
        // Инициализация mapView
        //mapView = binding.mapView
        /*java.lang.RuntimeException: Unable to start activity ComponentInfo{com.example.walknavigator/com.example.walknavigator.MainActivity}: java.lang.NullPointerException: findViewById(R.id.mapView) must not be null
        Caused by: java.lang.NullPointerException: findViewById(R.id.mapView) must not be null
        at com.example.walknavigator.MainActivity.onCreate(MainActivity.kt:27)*/
        //mapView.map.mapType = MapType.MAP
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, MainFragment())
                .commit()
        }
    }
    override fun onStart() {
        super.onStart()
        // Проверка на инициализацию mapView
        if (::mapView.isInitialized) {
            mapView.onStart()
            MapKitFactory.getInstance().onStart()
        }
    }
    override fun onStop() {
        super.onStop()
        if (::mapView.isInitialized) {
            mapView.onStop()
            MapKitFactory.getInstance().onStop()
        }
    }
}
