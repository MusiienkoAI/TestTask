package auto.data.usecases.api

import auto.data.entities.common.MainType
import auto.data.entities.common.Manufacturer
import auto.data.entities.requests.BuildDateRequest
import auto.data.entities.requests.MainTypeRequest
import auto.data.entities.requests.ManufacturesRequest
import auto.data.entities.responses.BuildDatesResponse
import auto.data.entities.responses.MainTypesResponse
import auto.data.entities.responses.ManufactureResponse
import auto.data.services.api.CarApiService
import auto.data.usecases.BaseUseCase
import auto.utilities.entities.Resource
import auto.utilities.testing.OpenForTesting
import io.reactivex.Observable
import io.reactivex.Single
import timber.log.Timber
import javax.inject.Inject


@OpenForTesting
class CarAPIUC @Inject constructor(private val carApiService: CarApiService) : BaseUseCase() {

    fun getManufactures(manufacturesRequest: ManufacturesRequest): Single<Resource<ManufactureResponse>> {
        Timber.d("getManufactures")
        return carApiService.getManufacturers(manufacturesRequest.page,manufacturesRequest.pageSize)
    }

    fun getMainTypes(mainTypeRequest: MainTypeRequest): Single<Resource<MainTypesResponse>>{
        return carApiService.getMainTypes(mainTypeRequest.page,mainTypeRequest.pageSize,mainTypeRequest.manufacturer)
    }

    fun getBuildDates(buildDateRequest: BuildDateRequest) :  Single<Resource<BuildDatesResponse>>{
        return carApiService.getBuildDates(buildDateRequest.manufacturer,buildDateRequest.mainType)
    }
}