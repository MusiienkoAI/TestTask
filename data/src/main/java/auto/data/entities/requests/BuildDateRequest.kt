package auto.data.entities.requests

import android.os.Parcelable
import auto.data.BuildConfig
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class BuildDateRequest (
    @SerializedName("manufacturer") val manufacturer: Int,
    @SerializedName("main-type") val mainType: String
): Parcelable