package auto.testtask.viewmodel.fragments

import androidx.lifecycle.MutableLiveData
import auto.data.entities.room.BuildDate
import auto.data.entities.requests.BuildDateRequest
import auto.data.entities.room.MainType
import auto.data.entities.room.Manufacturer
import auto.domain.interfaces.ICarInteractor
import auto.testtask.viewmodel.BaseViewModel
import javax.inject.Inject

class BuildDatesViewModel @Inject constructor(
        private val carInterractor: ICarInteractor
) : BaseViewModel() {



    var manufacture = MutableLiveData<Manufacturer>()
    var mainType = MutableLiveData<MainType>()

    var buildDates = MutableLiveData<ArrayList<BuildDate>>()




    fun requestBuildDates(manufactureId: String, mainTypeId: String) {
        if (buildDates.value.isNullOrEmpty())
            carInterractor.getBuildDates(BuildDateRequest(manufacturer = manufactureId,mainType = mainTypeId)).subscribe(
                    createSingleObserver(
                            next = {
                                buildDates.value = (it.buildDates)
                            })
            )
    }

    fun getInfo(manufactureId: String, mainTypeId: String){
        getManufacturer(manufactureId)
        getMainType(mainTypeId)
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





}