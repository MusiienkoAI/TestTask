package auto.data.entities.responses

import auto.data.entities.room.Manufacturer
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

data class ManufactureResponse(
    @SerializedName("page") val page: Int,
    @SerializedName("pageSize") val pageSize: Int,
    @SerializedName("totalPageCount") val totalPageCount: Int,
    @SerializedName("wkda") val data: JsonObject
){

    val manufacturers : ArrayList<Manufacturer>
    get() {
        val list = ArrayList<Manufacturer>()
        val keys = data.keySet()
        keys.forEach {
            val name = data.get(it).asString
            list.add(Manufacturer(it, name))
        }
        return list
    }
}