package auto.data.services.db.interfaces

import auto.data.entities.responses.ManufactureResponse
import auto.data.entities.room.Manufacturer
import io.reactivex.Single

interface IManufacturesDbService{

    fun saveManufactures(manufacturesResponse: ManufactureResponse): Single<ManufactureResponse>

    fun getManufacture(id: String): Single<Manufacturer>
}