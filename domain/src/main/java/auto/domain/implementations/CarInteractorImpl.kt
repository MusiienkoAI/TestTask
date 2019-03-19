package auto.domain.implementations

import auto.data.entities.requests.BuildDateRequest
import auto.data.entities.requests.MainTypeRequest
import auto.data.entities.requests.ManufacturesRequest
import auto.data.entities.responses.BuildDatesResponse
import auto.data.entities.responses.MainTypesResponse
import auto.data.entities.responses.ManufactureResponse
import auto.data.entities.room.BuildDate
import auto.data.entities.room.MainType
import auto.data.entities.room.Manufacturer
import auto.data.usecases.api.CarAPIUC
import auto.data.usecases.db.CarDBUC
import auto.utilities.SchedulersFacade
import auto.utilities.testing.OpenForTesting

import auto.domain.interfaces.ICarInteractor
import auto.utilities.extensions.fromResult

import io.reactivex.Single

import javax.inject.Inject
import javax.inject.Singleton


@Singleton
@OpenForTesting
class CarInteractorImpl @Inject constructor(
    schedulers: SchedulersFacade,
    private val carAPIUC: CarAPIUC,
    private val carDBUC: CarDBUC
) : BaseInteractorImpl(schedulers), ICarInteractor {


    override fun getManufactures(manufacturesRequest: ManufacturesRequest): Single<ManufactureResponse> {
        return carAPIUC.getManufactures(manufacturesRequest)
            .fromResult()
            .flatMap {
                carDBUC.saveManufactures(it)
            }
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
    }

    override fun getMainTypes(mainTypeRequest: MainTypeRequest): Single<MainTypesResponse> {
        return carAPIUC.getMainTypes(mainTypeRequest)
            .fromResult()
            .flatMap {
                carDBUC.saveMainTypes(it)
            }
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
    }

    override fun getBuildDates(buildDateRequest: BuildDateRequest): Single<BuildDatesResponse> {
        return carAPIUC.getBuildDates(buildDateRequest)
            .fromResult()
            .flatMap {
                carDBUC.saveBuildDatess(it)
            }
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
    }

    override fun getManufacture(id: String): Single<Manufacturer> {
        return carDBUC.getManufacture(id)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
    }

    override fun getMainType(id: String): Single<MainType> {
        return carDBUC.getMainType(id)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
    }

    override fun getBuildDate(id: String): Single<BuildDate> {
        return carDBUC.getBuildDate(id)
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
    }


}