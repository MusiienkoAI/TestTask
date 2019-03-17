package auto.data.entities.requests

import android.os.Parcelable
import auto.data.BuildConfig
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MainTypeRequest (
    @SerializedName("wa_key") var key: String? = BuildConfig.KEY,
    @SerializedName("manufacturer") val manufacturer: Int,
    @SerializedName("page") val page: Int=0,
    @SerializedName("pageSize") val pageSize: Int = 10
): Parcelable