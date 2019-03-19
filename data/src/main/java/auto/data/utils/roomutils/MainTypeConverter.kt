package auto.data.utils.roomutils


import androidx.room.TypeConverter
import auto.data.entities.room.MainType
import auto.utilities.extensions.fromJson
import auto.utilities.extensions.toJson
import com.google.gson.Gson

class MainTypeConverter {

    @TypeConverter
    fun mainTypeToString(mainType: MainType): String = mainType.toJson()

    @TypeConverter
    fun mainTypeFromString(mainTypeString: String?): MainType? {
        return if (mainTypeString == null) null else Gson().fromJson(mainTypeString)
    }
}