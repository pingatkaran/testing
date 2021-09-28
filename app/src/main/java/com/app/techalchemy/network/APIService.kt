package com.app.techalchemy.network

import com.app.techalchemy.model.EvenDetailsList
import com.app.techalchemy.model.PurchaseData
import com.google.gson.JsonObject
import retrofit2.http.*


interface APIService {

    @GET("bdcbafbc1f4197dda178b9e69f6ccee9/techAlchemyDeveloperTest1/allEvents")
    suspend fun getEvents(@Header("Authorization") authHeader: String): EvenDetailsList

    @GET("bdcbafbc1f4197dda178b9e69f6ccee9/techAlchemyDeveloperTest1/eventDetails")
    suspend fun getEventsDetails(@Header("Authorization") authHeader: String): EvenDetailsList



    @POST("bdcbafbc1f4197dda178b9e69f6ccee9/techAlchemyDeveloperTest1/purchase")
    @Headers("Accept: application/json")
    suspend fun postPurchaseEvent(@Header("Authorization") authHeader: String, @Body body: PurchaseData): PurchaseData

}