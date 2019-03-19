package auto.data.entities.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import auto.utilities.testing.OpenForTesting
import com.google.gson.annotations.SerializedName


@Entity
@OpenForTesting
data class BuildDate(
    @PrimaryKey
    @SerializedName("id") val id: String,
    @SerializedName("date") val date: String) {
    var manufacturerId: String? = null
    var mainTypeId: String? = null
}