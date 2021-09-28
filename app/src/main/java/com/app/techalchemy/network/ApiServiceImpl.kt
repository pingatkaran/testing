package com.app.techalchemy.network

import com.app.techalchemy.model.EvenDetailsList
import com.app.techalchemy.model.PurchaseData
import com.google.gson.JsonObject
import javax.inject.Inject

class ApiServiceImpl @Inject constructor(private val apiService: APIService) {

    suspend fun getEvents(): EvenDetailsList =
        apiService.getEventsDetails("Bearer 786785e9-1b74-430a-80d9-aae49678954f")

    suspend fun postEventPurchase(body: PurchaseData): PurchaseData = apiService.postPurchaseEvent(
        "Bearer 786785e9-1b74-430a-80d9-aae49678954f",
        body
    )

}