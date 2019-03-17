package auto.data.entities.common

import com.google.gson.annotations.SerializedName

data class Manufacturer (
    @SerializedName("id")  val id: String,
    @SerializedName("name")  val name: String
)