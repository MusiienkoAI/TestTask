package auto.testtask.viewmodel.fragments

import androidx.lifecycle.MutableLiveData
import auto.data.entities.common.Manufacturer
import auto.data.entities.requests.ManufacturesRequest
import auto.domain.interfaces.ICarInteractor
import auto.testtask.viewmodel.BaseViewModel
import auto.utilities.extensions.isPaginationAllowed
import javax.inject.Inject

class ManufacturesViewModel @Inject constructor(
    private val carInterractor: ICarInteractor
) : BaseViewModel() {


    private var currentPage = -1

    private var totalPageCount = 1

    private val pageSize = 15


    var manufactures = MutableLiveData<ArrayList<Manufacturer>>().apply {value = ArrayList()  }

    fun requestManufactures() {
        manufactures.value?.let {
            if(it.isPaginationAllowed(totalPageCount,pageSize))
                carInterractor.getManufactures(ManufacturesRequest(page = currentPage+1,pageSize = pageSize)).subscribe(
                        createSingleObserver(
                                next = {
                                    currentPage = it.page
                                    totalPageCount = it.totalPageCount
                                    val oldManufactures = manufactures.value
                                    oldManufactures?.addAll(it.manufacturers)
                                    manufactures.postValue(oldManufactures)

                                })
                )
        }
    }





}