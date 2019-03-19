package auto.data.services.db.implementors

import auto.data.db.CarDatabase
import auto.data.entities.responses.ManufactureResponse
import auto.data.entities.room.Manufacturer
import auto.data.services.db.interfaces.IManufacturesDbService
import auto.data.services.roomutil.RoomObservable
import io.reactivex.Single
import io.reactivex.functions.Function
import javax.inject.Inject

class ManufacturesDbServiceImpl @Inject constructor(
    val carDatabase: CarDatabase
) : IManufacturesDbService {

    override fun saveManufactures(manufacturesResponse: ManufactureResponse): Single<ManufactureResponse> =
        RoomObservable.createObject(
            carDatabase,
            Function<CarDatabase, ManufactureResponse> {
                it.manufacturesDao().saveManufactures(manufacturesResponse.manufacturers)
                return@Function manufacturesResponse
            }
        ).firstOrError()

    override fun getManufacture(id: String): Single<Manufacturer> = RoomObservable.createObject(
        carDatabase,
        Function<CarDatabase, Manufacturer> {
            it.manufacturesDao().getManufacture(id) ?: throw UnknownError()
        }
    ).firstOrError()
}
