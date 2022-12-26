package com.example.navcomponentpractice

import android.os.Parcel
import android.os.Parcelable

class TempModel(var str: String?, val tempInterface: TempInterface?): Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readParcelable(TempInterface::class.java.classLoader)
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(str)
        parcel.writeParcelable(tempInterface, flags)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<TempModel> {
        override fun createFromParcel(parcel: Parcel): TempModel {
            return TempModel(parcel)
        }

        override fun newArray(size: Int): Array<TempModel?> {
            return arrayOfNulls(size)
        }
    }


}