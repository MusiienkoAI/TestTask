package auto.data.db

import androidx.room.*
import auto.data.entities.room.BuildDate
import auto.data.entities.room.MainType


@Dao
interface BuildDatesDao{
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveBuildDate(buildDate: BuildDate)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveBuildDates(buildDate: List<BuildDate>)

    @Query("SELECT * FROM buildDate WHERE id = :id")
    fun getBuildDate(id: String): BuildDate?

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateBuildDate(buildDate: BuildDate)
}