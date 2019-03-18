package auto.data.db

import androidx.room.*
import auto.data.entities.room.CarData


@Dao
interface ManufacturesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveManufactures(carData: CarData)

    @Query("SELECT * FROM carData")
    fun getManufacture(): CarData?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateManufacture(carData: CarData)


}