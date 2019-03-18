package auto.data.services.api

import auto.data.entities.common.MainType
import auto.data.entities.common.Manufacturer
import auto.data.entities.requests.BuildDateRequest
import auto.data.entities.requests.MainTypeRequest
import auto.data.entities.requests.ManufacturesRequest
import auto.data.entities.responses.BuildDatesResponse
import auto.data.entities.responses.MainTypesResponse
import auto.data.entities.responses.ManufactureResponse
import auto.utilities.entities.Resource
import io.reactivex.Observable
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


interface CarApiService {

    @GET("v1/car-types/manufacturer")
    fun getManufacturers(@Query("page") page: Int, @Query("pageSize") pageSize: Int): Observable<Resource<ManufactureResponse>>

    @GET("v1/car-types/main-types")
    fun getMainTypes(@Body mainTypeRequest: MainTypeRequest): Observable<Resource<MainTypesResponse>>

    @GET("v1/car-types/built-dates")
    fun getBuildDates(@Body buildDateRequest: BuildDateRequest): Observable<Resource<BuildDatesResponse>>

}