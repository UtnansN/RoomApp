package com.example.spaceapp.data.model

import android.os.Parcel
import android.os.Parcelable

class EventBriefDTO (
    val eventId: Long = 0,
    val name: String? = "",
    val description: String? = "",
    val dateTime: String? = "",
    val location: String? = "",
) : Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readLong(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString()
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeLong(eventId)
        parcel.writeString(name)
        parcel.writeString(description)
        parcel.writeString(dateTime)
        parcel.writeString(location)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<EventBriefDTO> {
        override fun createFromParcel(parcel: Parcel): EventBriefDTO {
            return EventBriefDTO(parcel)
        }

        override fun newArray(size: Int): Array<EventBriefDTO?> {
            return arrayOfNulls(size)
        }
    }

}
