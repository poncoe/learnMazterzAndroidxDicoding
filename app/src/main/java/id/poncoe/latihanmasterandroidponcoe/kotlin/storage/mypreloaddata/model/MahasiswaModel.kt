package id.poncoe.latihanmasterandroidponcoe.kotlin.storage.mypreloaddata.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MahasiswaModel(
        var id: Int = 0,
        var name: String? = null,
        var nim: String? = null
) : Parcelable