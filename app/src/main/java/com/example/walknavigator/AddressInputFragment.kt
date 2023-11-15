package com.example.walknavigator

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.walknavigator.databinding.FragmentAddressInputBinding
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.search.*
import com.yandex.mapkit.search.Session.SearchListener
import java.util.*

class AddressInputFragment : Fragment() {

    private lateinit var binding: FragmentAddressInputBinding
    private lateinit var addressListAdapter: AddressListAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAddressInputBinding.inflate(inflater, container, false)
        val rootView = binding.root

        // Установка ключа API
        MapKitFactory.setApiKey(BuildConfig.MAPKIT_API_KEY)

        val addressInput = binding.addressInput
        val searchButton = binding.searchButton
        val addressList = binding.addressList

        // Просто для примера - список адресов
        val addresses = listOf("Улица 1, 12", "Улица 2, 34", "Улица 3, 56")

        addressListAdapter = AddressListAdapter(object : AddressListAdapter.OnItemClickListener {
            override fun onItemClick(address: String) {
                // Обработка выбора адреса
                // Например, передача выбранного адреса в функцию на главном экране
                // Пример: (activity as? YourMainActivity)?.updateFinishText(address)
            }
        })

        addressList.layoutManager = LinearLayoutManager(context)
        addressList.adapter = addressListAdapter

        // Настройка обработчика нажатия на кнопку поиска
        searchButton.setOnClickListener {
            val searchText = addressInput.text.toString()
            // Выполните поиск адресов и обновите список addressListAdapter
            searchAddresses(searchText)
        }

        return rootView
    }

    private fun searchAddresses(query: String) {
        val searchManager = SearchFactory.getInstance().createSearchManager(SearchManagerType.COMBINED)

        val searchOptions = SearchOptions().apply {
            searchTypes = SearchType.Biz // не работает
            resultPageSize = 32
        }


        searchManager.submit( // не работает
            query,
            searchOptions,
            object : Session.SearchListener {
                override fun onSearchResponse(response: Response) {
                    if (response.collection.children.isNotEmpty()) {
                        val addresses = response.collection.children.mapNotNull { it.obj?.name }
                        // Обновляем список адресов
                        addressListAdapter.updateAddresses(addresses)
                    }
                }

                override fun onSearchError(p0: com.yandex.runtime.Error) {
                    TODO("Not yet implemented")
                }
            }
        )
    }
}

