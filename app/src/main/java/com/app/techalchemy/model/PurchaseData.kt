package com.app.techalchemy.model

data class PurchaseData(
	val purchase: Purchase
)

data class Purchase (
	val dateTime : String,
	val purchaseAmount : Double,
	val paymentMethodType : String,
	val eventId : Int,
	val id : Int?=null
)