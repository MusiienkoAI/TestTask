package auto.data.entities.responses

import auto.data.entities.room.BuildDate
import com.google.gson.JsonObject
import com.google.gson.annotations.SerializedName

class BuildDatesResponse (
    @SerializedName("wkda") val data: JsonObject
) {

    val buildDates: ArrayList<BuildDate>
        get() {
            val list = ArrayList<BuildDate>()
            val keys = data.keySet()
            keys.forEach {
                val name = data.get(it).asString
                list.add(BuildDate(it,name))
            }
            return list
        }
}