package com.example.walknavigator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.walknavigator.databinding.FragmentAddressInputBinding
import com.yandex.mapkit.MapKit
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.VisibleRegionUtils
import com.yandex.mapkit.mapview.MapView
import com.yandex.mapkit.search.*


class AddressInputFragment : Fragment() {
    val latitude = arguments?.getDouble("latitude", 0.0) ?: 0.0
    val longitude = arguments?.getDouble("longitude", 0.0) ?: 0.0


    private lateinit var binding: FragmentAddressInputBinding
    private lateinit var addressListAdapter: AddressListAdapter
    private lateinit var mapView: MapView  // Добавил переменную mapView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddressInputBinding.inflate(inflater, container, false)
        val rootView = binding.root
        mapView = arguments?.getParcelable("mapView") ?: throw IllegalArgumentException("MapView not found")


        // Установка ключа API
        // Инициализация MapKit и другие действия
        MapKitFactory.setApiKey(BuildConfig.MAPKIT_API_KEY)
        MapKitFactory.getInstance().onStart()
        mapView.onStart()


        val addressInput = binding.addressInput
        val searchButton = binding.searchButton
        val addressList = binding.addressList

        val startButton = binding.startButton

        startButton.setOnClickListener {
            binding.addressInputLayout.visibility = View.GONE
            binding.stopButton.visibility = View.VISIBLE
            showRoute()
        }
        val centerButton = binding.centerButton
        val stopButton = binding.stopButton

        centerButton.setOnClickListener {
            // ... (код для центрирования на координатах пользователя)
        }

        stopButton.setOnClickListener {
            binding.addressInputLayout.visibility = View.VISIBLE
            binding.stopButton.visibility = View.GONE
            mapView.map.mapObjects.clear()
        }

        // Просто для примера - список адресов
        val addresses = listOf("Улица 1, 12", "Улица 2, 34", "Улица 3, 56")

        addressListAdapter = AddressListAdapter(object : AddressListAdapter.OnItemClickListener {
            override fun onItemClick(address: String) {
                // Обработка выбора адреса
                // Например, передача выбранного адреса в функцию на главном экране
                // Пример: (activity as? YourMainActivity)?.updateFinishText(address)
            }
        })
        val latitude = arguments?.getDouble("latitude", 0.0) ?: 0.0
        val longitude = arguments?.getDouble("longitude", 0.0) ?: 0.0

        mapView = MapView(requireContext())
        mapView.map.move(
            CameraPosition(
                Point(latitude, longitude),
                /* zoom = */ 17.0f,
                /* azimuth = */ 150.0f,
                /* tilt = */ 30.0f
            )
        )

        addressList.layoutManager = LinearLayoutManager(context)
        addressList.adapter = addressListAdapter

        // Настройка обработчика нажатия на кнопку поиска
        searchButton.setOnClickListener {
            val searchText = addressInput.text.toString()
            // Выполните поиск адресов и обновите список addressListAdapter
            searchAddresses(searchText, object : Session.SearchListener{
                override fun onSearchResponse(response: Response) {
                    if (response.collection.children.isNotEmpty()) {
                        val addresses = response.collection.children.mapNotNull { it.obj?.name }
                        // Обновляем список адресов
                        addressListAdapter.updateAddresses(addresses)
                    }
                }

                override fun onSearchError(error: com.yandex.runtime.Error) {
                    // Обработка ошибки поиска
                }
            })
        }

        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val latitude = arguments?.getDouble("latitude", 0.0) ?: 0.0
        val longitude = arguments?.getDouble("longitude", 0.0) ?: 0.0
        mapView.map.move(
            CameraPosition(
                Point(latitude, longitude),
                17.0f,
                150.0f,
                30.0f
            )
        )
    }

    private fun searchAddresses(query: String, searchListener: Session.SearchListener){
        val searchManager = SearchFactory.getInstance().createSearchManager(SearchManagerType.COMBINED)

        val searchOptions = SearchOptions().apply {
            searchTypes = SearchType.GEO.value
            resultPageSize = 32
        }

        val visibleBounds = mapView.map.cameraPosition.target
        val session = searchManager.submit(
            query,
            VisibleRegionUtils.toPolygon(mapView.map.visibleRegion),
            searchOptions,
            searchListener
        )
    }
    private fun showRoute() {
        // ... (ваш существующий код)
    }
}

