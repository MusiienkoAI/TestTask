package auto.domain.interfaces

import auto.data.entities.requests.BuildDateRequest
import auto.data.entities.requests.MainTypeRequest
import auto.data.entities.requests.ManufacturesRequest
import auto.data.entities.responses.BuildDatesResponse
import auto.data.entities.responses.MainTypesResponse
import auto.data.entities.responses.ManufactureResponse
import auto.data.entities.room.BuildDate
import auto.data.entities.room.MainType
import auto.data.entities.room.Manufacturer

import io.reactivex.Single


interface ICarInteractor : IBaseInteractor {

    fun getManufactures(manufacturesRequest: ManufacturesRequest): Single<ManufactureResponse>

    fun getMainTypes(mainTypeRequest: MainTypeRequest) : Single<MainTypesResponse>

    fun getBuildDates(buildDateRequest: BuildDateRequest) : Single<BuildDatesResponse>

    fun getManufacture(id : String) : Single<Manufacturer>

    fun getMainType(id : String) : Single<MainType>

    fun getBuildDate(id : String) : Single<BuildDate>

}