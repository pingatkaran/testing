package com.app.techalchemy.model

import android.os.Parcel
import android.os.Parcelable

data class EvenDetailsList (
	val eventDetails : List<EventDetails>
)

data class EventDetails (
	val name : String,
	val dateTime : String,
	val bookBy : String,
	val ticketsSold : Long,
	val maxTickets : Long,
	val friendsAttending : Float,
	val price : Double,
	val isPartnered : Boolean,
	val sport : String,
	val totalPrize : Float,
	val location : String,
	val description : String,
	val venueInformation : String,
	val eventCreator : String,
	val teamInformation : String,
	val tags : String,
	val mainImage : String,
	val id : Int
) : Parcelable {
	constructor(parcel: Parcel) : this(
		parcel.readString()!!,
		parcel.readString()!!,
		parcel.readString()!!,
		parcel.readLong(),
		parcel.readLong(),
		parcel.readFloat(),
		parcel.readDouble(),
		parcel.readByte() != 0.toByte(),
		parcel.readString()!!,
		parcel.readFloat()!!,
		parcel.readString()!!,
		parcel.readString()!!,
		parcel.readString()!!,
		parcel.readString()!!,
		parcel.readString()!!,
		parcel.readString()!!,
		parcel.readString()!!,
		parcel.readInt()
	) {
	}

	override fun describeContents(): Int {
		return 0
	}

	override fun writeToParcel(dest: Parcel, flags: Int) {

	}

	companion object CREATOR : Parcelable.Creator<EventDetails> {
		override fun createFromParcel(parcel: Parcel): EventDetails {
			return EventDetails(parcel)
		}

		override fun newArray(size: Int): Array<EventDetails?> {
			return arrayOfNulls(size)
		}
	}
}