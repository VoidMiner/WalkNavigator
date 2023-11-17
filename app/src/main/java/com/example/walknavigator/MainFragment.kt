package com.example.walknavigator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.walknavigator.databinding.FragmentMainBinding
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.mapview.MapView

class MainFragment : Fragment() {
    private lateinit var binding: FragmentMainBinding
    private lateinit var mapView: MapView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMainBinding.inflate(inflater, container, false)
        val rootView = binding.root
        // Установка ключа API
        //MapKitFactory.setApiKey(BuildConfig.MAPKIT_API_KEY)

        MapKitFactory.initialize(requireContext())

        val latitude = arguments?.getDouble("latitude", 0.0) ?: 0.0
        val longitude = arguments?.getDouble("longitude", 0.0) ?: 0.0

        // Настройка карты
        // Используйте binding.mapView
        binding.mapView.map.move(
            CameraPosition(
                Point(latitude, longitude),
                /* zoom = */ 17.0f,
                /* azimuth = */ 150.0f,
                /* tilt = */ 30.0f
            )
        )


        return rootView
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        // Добавьте обработчик для кнопки "Старт"
        binding.openAddressInputButton.setOnClickListener {
            onStartClicked(it)
        }

    }

    fun onStartClicked(view: View) {
        // Ваш текущий код

        // Добавьте код для открытия AddressInputFragment
        val addressInputFragment = AddressInputFragment()
        val bundle = Bundle()
        bundle.putDouble("latitude", 55.751225)
        bundle.putDouble("longitude", 37.629540)
        addressInputFragment.arguments = bundle

        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.fragmentContainer, addressInputFragment)
        transaction.addToBackStack(null)  // Добавляет транзакцию в стек возврата
        transaction.commit()
    }
}
