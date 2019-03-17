package auto.data.entities.room


import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import auto.data.entities.common.MainType
import auto.data.entities.common.Manufacturer
import auto.utilities.testing.OpenForTesting
import auto.data.utils.roomutils.MainTypeConverter
import auto.data.utils.roomutils.ManufacturerTypeConverter


import com.google.gson.annotations.SerializedName


@Entity
@OpenForTesting
@TypeConverters(MainTypeConverter::class, ManufacturerTypeConverter::class)
data class CarData(
    @PrimaryKey @SerializedName("id") val id: Int,
    @SerializedName("name") var name: String,
    @SerializedName("year") var year: Int,
    @Embedded(prefix = "main_type_") @SerializedName("main_type") var mainType: MainType,
    @Embedded(prefix = "manufacturer_") @SerializedName("manufacturer") var manufacturer: Manufacturer

)