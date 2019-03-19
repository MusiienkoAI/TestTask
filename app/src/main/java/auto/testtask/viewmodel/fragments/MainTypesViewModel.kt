package auto.testtask.viewmodel.fragments

import androidx.lifecycle.MutableLiveData
import auto.data.entities.room.MainType
import auto.data.entities.requests.MainTypeRequest
import auto.data.entities.room.Manufacturer
import auto.domain.interfaces.ICarInteractor
import auto.testtask.viewmodel.BaseViewModel
import auto.utilities.extensions.isRequestAllowed
import javax.inject.Inject

class MainTypesViewModel@Inject constructor(
        private val carInterractor: ICarInteractor
) : BaseViewModel() {


    private var currentPage = -1

    private var totalPageCount = 1

    private val pageSize = 15

    val manufacture = MutableLiveData<Manufacturer>()


    var mainTypes = MutableLiveData<ArrayList<MainType>>().apply {value = ArrayList()  }

    fun requestMainTypes(manufactureId: String) {
        mainTypes.value?.let {
            if(it.isRequestAllowed(totalPageCount,pageSize))
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

    fun getManufacturer(id: String){
        carInterractor.getManufacture(id).subscribe(createSingleObserver(
            next = {
                manufacture.value = it
            }
        ))
    }



}