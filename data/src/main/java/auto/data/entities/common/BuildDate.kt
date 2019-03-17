package auto.data.entities.common

import com.google.gson.annotations.SerializedName

data class BuildDate(
   @SerializedName("date") val date: String
){
    var manufacturerId: String? = null
    var mainTypeId : String? = null
}