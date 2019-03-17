package auto.domain.implementations

import auto.data.entities.common.MainType
import auto.data.entities.common.Manufacturer
import auto.data.entities.requests.BuildDateRequest
import auto.data.entities.requests.MainTypeRequest
import auto.data.entities.requests.ManufacturesRequest
import auto.data.entities.responses.ManufactureResponse
import auto.data.usecases.api.CarAPIUC
import auto.utilities.SchedulersFacade
import auto.utilities.testing.OpenForTesting

import auto.domain.interfaces.ICarInteractor
import auto.utilities.entities.Resource
import auto.utilities.extensions.fromResult

import io.reactivex.Single
import timber.log.Timber

import javax.inject.Inject
import javax.inject.Singleton


@Singleton
@OpenForTesting
class CarInteractorImpl @Inject constructor(
    schedulers: SchedulersFacade,
    private val carAPIUC: CarAPIUC
) : BaseInteractorImpl(schedulers), ICarInteractor {

    override fun getManufactures(manufacturesRequest: ManufacturesRequest): Single<ManufactureResponse> {
        Timber.d("getManufactures")
        return carAPIUC.getManufactures(manufacturesRequest)
            .fromResult()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
    }

    override fun getMainTypes(mainTypeRequest: MainTypeRequest): Single<List<MainType>> {
        return carAPIUC.getMainTypes(mainTypeRequest)
            .fromResult()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
    }

    override fun getBuildDates(buildDateRequest: BuildDateRequest): Single<List<String>> {
        return carAPIUC.getBuildDates(buildDateRequest)
            .fromResult()
            .subscribeOn(schedulers.io())
            .observeOn(schedulers.ui())
    }



}