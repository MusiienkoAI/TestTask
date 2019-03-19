package auto.data.db


import androidx.room.Database
import androidx.room.RoomDatabase
import auto.data.entities.room.BuildDate
import auto.data.entities.room.MainType
import auto.data.entities.room.Manufacturer


@Database(
    entities = [
        MainType::class,
        Manufacturer::class,
        BuildDate::class
    ],
    version = 1,
    exportSchema = false
)
abstract class CarDatabase : RoomDatabase() {

    abstract fun manufacturesDao(): ManufacturesDao

    abstract fun mainTypesDao(): MainTypesDao

    abstract fun buildDatesDao(): BuildDatesDao
}