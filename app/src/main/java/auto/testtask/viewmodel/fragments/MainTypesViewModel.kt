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


    private var currentPage = 0

    private var totalPageCount = 1

    private val pageSize = 10


    var mainTypes = MutableLiveData<ArrayList<MainType>>()

    fun requestMainTypes(manufactureId: String) {
        if (mainTypes.value.isNullOrEmpty())
            carInterractor.getMainTypes(MainTypeRequest(page = currentPage,pageSize = pageSize,manufacturer = manufactureId)).subscribe(
                    createSingleObserver(
                            next = {
                                currentPage = it.page
                                totalPageCount = it.totalPageCount
                                mainTypes.value = (it.mainTypes)
                            })
            )
    }


    fun requestMoreMainTypes(manufactureId: String){
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