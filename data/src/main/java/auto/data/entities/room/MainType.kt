package auto.data.entities.room

import androidx.room.Entity
import androidx.room.PrimaryKey
import auto.utilities.testing.OpenForTesting
import com.google.gson.annotations.SerializedName

@Entity
@OpenForTesting
data class MainType(
    @PrimaryKey
    @SerializedName("id") val id: String,
    @SerializedName("name") val name: String

    ) {
    var manufacturer: String? = null
}