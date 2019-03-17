package auto.data.db


import androidx.room.Database
import androidx.room.RoomDatabase
import auto.data.entities.room.CarData


@Database(
    entities = [
        CarData::class
    ],
    version = 1,
    exportSchema = false
)
abstract class CarDatabase: RoomDatabase() {

    abstract fun userDao(): UserDao
}