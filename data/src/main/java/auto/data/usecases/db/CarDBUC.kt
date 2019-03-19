package auto.data.usecases.db

import auto.data.entities.responses.BuildDatesResponse
import auto.data.entities.responses.MainTypesResponse
import auto.data.entities.responses.ManufactureResponse
import auto.data.entities.room.BuildDate
import auto.data.entities.room.MainType
import auto.data.entities.room.Manufacturer
import auto.data.services.db.interfaces.IBuildDatesDbService
import auto.data.services.db.interfaces.IMainTypesDbService
import auto.data.services.db.interfaces.IManufacturesDbService
import auto.data.usecases.BaseUseCase
import auto.utilities.testing.OpenForTesting

import io.reactivex.Single
import javax.inject.Inject


@OpenForTesting
class CarDBUC @Inject constructor(
    private val manufacturesDbService: IManufacturesDbService,
    private val mainTypesDbService: IMainTypesDbService,
    private val buildDatesDbService: IBuildDatesDbService
    ) : BaseUseCase() {

    fun saveManufactures(manufactures: ManufactureResponse): Single<ManufactureResponse> {
        return manufacturesDbService.saveManufactures(manufactures)
    }

    fun saveMainTypes(mainTypes:MainTypesResponse): Single<MainTypesResponse> {
        return mainTypesDbService.saveMainTypes(mainTypes)
    }

    fun saveBuildDatess(buildDates: BuildDatesResponse): Single<BuildDatesResponse> {
        return buildDatesDbService.saveBuildDates(buildDates)
    }


    fun getManufacture(id: String): Single<Manufacturer> {
        return manufacturesDbService.getManufacture(id)
    }

    fun getMainType(id: String): Single<MainType> {
        return mainTypesDbService.getMainType(id)
    }

    fun getBuildDate(id: String): Single<BuildDate> {
        return buildDatesDbService.getBuildDate(id)
    }
}