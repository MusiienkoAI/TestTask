package auto.testtask.viewmodel.fragments

import androidx.lifecycle.MutableLiveData
import auto.data.entities.room.BuildDate
import auto.data.entities.room.MainType
import auto.data.entities.room.Manufacturer
import auto.domain.interfaces.ICarInteractor
import auto.testtask.viewmodel.BaseViewModel
import javax.inject.Inject

class SummaryViewModel @Inject constructor(
        private val carInterractor: ICarInteractor
) : BaseViewModel() {

    val manufacture = MutableLiveData<Manufacturer>()
    val mainType = MutableLiveData<MainType>()
    val buildDate = MutableLiveData<BuildDate>()


    fun getSummary(manufactureId: String, mainTypeId: String, buildDate: String) {
        getManufacturer(manufactureId)
        getMainType(mainTypeId)
        getBuildDate(buildDate)
    }

    private fun getManufacturer(id: String) {
        carInterractor.getManufacture(id).subscribe(createSingleObserver(
                next = {
                    manufacture.value = it
                }
        ))
    }

    private fun getMainType(id: String) {
        carInterractor.getMainType(id).subscribe(createSingleObserver(
                next = {
                    mainType.value = it
                }
        ))
    }

    private fun getBuildDate(id: String) {
        carInterractor.getBuildDate(id).subscribe(createSingleObserver(
                next = {
                    buildDate.value = it
                }
        ))
    }


}