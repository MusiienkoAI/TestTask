package auto.data.db

import androidx.room.*
import auto.data.entities.room.MainType
import auto.data.entities.room.Manufacturer

@Dao
interface MainTypesDao{

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMainType(mainType: MainType)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveMainTypes(mainType: List<MainType>)

    @Query("SELECT * FROM mainType WHERE id = :id")
    fun getMainType(id: String): MainType?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateMainType(mainType: MainType)
}