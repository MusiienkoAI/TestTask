package auto.data.services.db.implementors

import auto.data.db.CarDatabase
import auto.data.entities.responses.MainTypesResponse
import auto.data.entities.room.MainType
import auto.data.services.db.interfaces.IMainTypesDbService
import auto.data.services.roomutil.RoomObservable
import auto.utilities.testing.OpenForTesting
import io.reactivex.Single
import io.reactivex.functions.Function
import javax.inject.Inject

@OpenForTesting
class MainTypesDbServiceImpl @Inject constructor(
    val carDatabase: CarDatabase
) : IMainTypesDbService {

    override fun saveMainTypes(mainTypesResponse: MainTypesResponse): Single<MainTypesResponse> = RoomObservable.createObject(
        carDatabase,
        Function<CarDatabase,MainTypesResponse> {
            it.mainTypesDao().saveMainTypes(mainTypesResponse.mainTypes)
            return@Function mainTypesResponse
        }
    ).firstOrError()

    override fun getMainType(id: String): Single<MainType> = RoomObservable.createObject(
        carDatabase,
        Function<CarDatabase, MainType> {
            it.mainTypesDao().getMainType(id) ?: throw UnknownError()
        }
    ).firstOrError()
}