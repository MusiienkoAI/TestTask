package auto.testtask.viewmodel.fragments

import androidx.lifecycle.MutableLiveData
import auto.data.entities.common.MainType
import auto.data.entities.requests.MainTypeRequest
import auto.domain.interfaces.ICarInteractor
import auto.testtask.viewmodel.BaseViewModel
import auto.utilities.extensions.isPaginationAllowed
import javax.inject.Inject

class MainTypesViewModel@Inject constructor(
        private val carInterractor: ICarInteractor
) : BaseViewModel() {


    private var currentPage = -1

    private var totalPageCount = 1

    private val pageSize = 15


    var mainTypes = MutableLiveData<ArrayList<MainType>>().apply {value = ArrayList()  }

    fun requestMainTypes(manufactureId: String) {
        mainTypes.value?.let {
            if(it.isPaginationAllowed(totalPageCount,pageSize))
                carInterractor.getMainTypes(MainTypeRequest(page = currentPage+1,pageSize = pageSize,manufacturer = manufactureId)).subscribe(
                        createSingleObserver(
                                next = {
                                    currentPage = it.page
                                    totalPageCount = it.totalPageCount
                                    val oldManufactures = mainTypes.value
                                    oldManufactures?.addAll(it.mainTypes)
                                    mainTypes.postValue(oldManufactures)

                                })
                )
        }
    }



}