package auto.domain.interfaces

import auto.data.entities.common.MainType
import auto.data.entities.common.Manufacturer
import auto.data.entities.requests.BuildDateRequest
import auto.data.entities.requests.MainTypeRequest
import auto.data.entities.requests.ManufacturesRequest
import auto.data.entities.responses.ManufactureResponse
import auto.utilities.entities.Resource

import io.reactivex.Single


interface ICarInteractor : IBaseInteractor {

    fun getManufactures(manufacturesRequest: ManufacturesRequest): Single<ManufactureResponse>

    fun getMainTypes(mainTypeRequest: MainTypeRequest) : Single<List<MainType>>

    fun getBuildDates(buildDateRequest: BuildDateRequest) : Single<List<String>>



}