package auto.data.utils.roomutils

import androidx.room.TypeConverter
import auto.data.entities.room.Manufacturer
import auto.utilities.extensions.fromJson
import auto.utilities.extensions.toJson
import com.google.gson.Gson

class ManufacturerTypeConverter {

    @TypeConverter
    fun manufacturerToString(carList: Manufacturer): String = carList.toJson()

    @TypeConverter
    fun manufacturerFromString(manufacturerString : String?): Manufacturer? {
        return if (manufacturerString == null) null else Gson().fromJson(manufacturerString)
    }
}