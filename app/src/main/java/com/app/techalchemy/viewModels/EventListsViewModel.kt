package com.app.techalchemy.viewModels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.techalchemy.model.EvenDetailsList
import com.app.techalchemy.model.Purchase
import com.app.techalchemy.model.PurchaseData
import com.app.techalchemy.repository.MainRepository
import com.app.techalchemy.utils.Resource
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import org.json.JSONException
import org.json.JSONObject
import javax.inject.Inject


@HiltViewModel
class EventListsViewModel @Inject constructor(val mainRepository: MainRepository) : ViewModel() {

    private val postStateFlow = MutableStateFlow<Resource<EvenDetailsList>>(Resource.empty())

    val postState : StateFlow<Resource<EvenDetailsList>> = postStateFlow

    private val purchaseStateFlow = MutableStateFlow<Resource<PurchaseData>>(Resource.empty())

    val purchaseState : StateFlow<Resource<PurchaseData>> = purchaseStateFlow

    init {
        getEvents()
    }

    fun getEvents() = viewModelScope.launch {
        mainRepository.getEvents()
            .onStart {
                postStateFlow.value = (Resource.loading(null))
            }
            .catch { e ->
                postStateFlow.value =(Resource.error(e.message.toString(),null))
            }
            .collect {
                postStateFlow.value = (Resource.success(it))
            }
    }

    fun postEventPuchase(dateTime:String, purchaseAmt: Double, eventId: Int) = viewModelScope.launch {

        val response = PurchaseData(
            Purchase(dateTime,purchaseAmt,"visa", eventId))

        mainRepository.postEventPuchase(response)
            .onStart {
                purchaseStateFlow.value = (Resource.loading(null))
            }
            .catch { e ->
                purchaseStateFlow.value =(Resource.error(e.message.toString(),null))
            }
            .collect {
                purchaseStateFlow.value = (Resource.success(it))
            }
    }


}