package com.example.lab2

import android.os.Parcel
import android.os.Parcelable

class Item(var graphic: String, var name: String, var helptext : String?) : Parcelable {

    override fun writeToParcel(parcel: Parcel?, flags: Int) {
        if (parcel != null) {
            parcel.writeString(graphic)
            parcel.writeString(name)
            parcel.writeString(helptext)
        }
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Item> {
        override fun createFromParcel(parcel: Parcel): Item {
            val graphic = parcel.readString()
            val name = parcel.readString()
            val helptext = parcel.readString()
            return Item(graphic, name, helptext)
        }

        override fun newArray(size: Int): Array<Item?> {
            return arrayOfNulls(size)
        }
    }
}
