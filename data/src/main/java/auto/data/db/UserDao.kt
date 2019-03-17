package auto.data.db


import androidx.room.*
import auto.data.entities.room.CarData

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveUser(carData: CarData)

    @Query("SELECT * FROM carData")
    fun getUser(): CarData?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateUser(carData: CarData)

    @Query("DELETE FROM CarData")
    fun deleteUser()
}