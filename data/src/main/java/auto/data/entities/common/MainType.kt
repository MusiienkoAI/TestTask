package auto.data.entities.common

import com.google.gson.annotations.SerializedName

data class MainType(
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String

    ) {
    val manufacturer: String? = null
}