package auto.data.services.db.interfaces

import auto.data.entities.responses.BuildDatesResponse
import auto.data.entities.room.BuildDate
import io.reactivex.Single

interface IBuildDatesDbService {
    fun saveBuildDates(buildDatesResponse: BuildDatesResponse): Single<BuildDatesResponse>

    fun getBuildDate(id: String): Single<BuildDate>
}