package auto.data.entities.responses

import auto.data.entities.common.MainType
import auto.data.entities.common.Manufacturer
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class MainTypesResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("pageSize") val pageSize: Int,
    @SerializedName("totalPageCount") val totalPageCount: Int,
    @SerializedName("wkda") val data: JsonObject
) {

    val mainTypes: ArrayList<MainType>
        get() {
            val list = ArrayList<MainType>()
            val keys = data.keySet()
            keys.forEach {
                val name = data.get(it).asString
                list.add(MainType(it, name))
            }
            return list
        }
}