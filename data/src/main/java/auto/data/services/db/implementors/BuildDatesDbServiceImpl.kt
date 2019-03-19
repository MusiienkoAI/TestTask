package auto.data.services.db.implementors

import auto.data.db.CarDatabase
import auto.data.entities.responses.BuildDatesResponse
import auto.data.entities.room.BuildDate
import auto.data.services.db.interfaces.IBuildDatesDbService
import auto.data.services.roomutil.RoomObservable
import auto.utilities.testing.OpenForTesting
import io.reactivex.Single
import io.reactivex.functions.Function

import javax.inject.Inject

@OpenForTesting
class BuildDatesDbServiceImpl @Inject constructor(
    val carDatabase: CarDatabase
) : IBuildDatesDbService {


    override fun saveBuildDates(buildDatesResponse: BuildDatesResponse): Single<BuildDatesResponse> = RoomObservable.createObject(
        carDatabase,
        Function<CarDatabase,BuildDatesResponse>{
            it.buildDatesDao().saveBuildDates(buildDatesResponse.buildDates)
            return@Function buildDatesResponse
        }
    ).firstOrError()

    override fun getBuildDate(id: String): Single<BuildDate>  = RoomObservable.createObject(
        carDatabase,
        Function<CarDatabase,BuildDate>{
            it.buildDatesDao().getBuildDate(id) ?: throw UnknownError()
        }
    ).firstOrError()
}