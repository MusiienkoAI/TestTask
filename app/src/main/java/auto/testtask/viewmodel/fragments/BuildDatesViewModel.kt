package auto.testtask.viewmodel.fragments

import androidx.lifecycle.MutableLiveData
import auto.data.entities.common.BuildDate
import auto.data.entities.common.MainType
import auto.data.entities.requests.BuildDateRequest
import auto.data.entities.requests.MainTypeRequest
import auto.domain.interfaces.ICarInteractor
import auto.testtask.viewmodel.BaseViewModel
import javax.inject.Inject

class BuildDatesViewModel @Inject constructor(
        private val carInterractor: ICarInteractor
) : BaseViewModel() {




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


}