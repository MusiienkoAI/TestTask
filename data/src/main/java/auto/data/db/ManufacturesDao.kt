package auto.data.db

import androidx.room.*
import auto.data.entities.room.Manufacturer


@Dao
interface ManufacturesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveManufacture(manufacturer: Manufacturer)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveManufactures(manufacturer: List<Manufacturer>)

    @Query("SELECT * FROM manufacturer WHERE id = :id")
    fun getManufacture(id : String): Manufacturer?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateManufacture(manufacturer: Manufacturer)




}