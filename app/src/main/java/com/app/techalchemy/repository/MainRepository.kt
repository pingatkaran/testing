package com.app.techalchemy.repository

import com.app.techalchemy.model.EvenDetailsList
import com.app.techalchemy.model.PurchaseData
import com.app.techalchemy.network.ApiServiceImpl
import com.google.gson.JsonObject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import javax.inject.Inject

class MainRepository @Inject constructor(private val apiService: ApiServiceImpl)  {

    fun getEvents(): Flow<EvenDetailsList> = flow {
        emit(apiService.getEvents())
    }.flowOn(Dispatchers.IO)

    fun postEventPuchase(body: PurchaseData): Flow<PurchaseData> = flow {
        emit(apiService.postEventPurchase(body))
    }.flowOn(Dispatchers.IO)
}