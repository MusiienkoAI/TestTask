package auto.testtask.viewmodel.fragments

import auto.data.entities.requests.ManufacturesRequest
import auto.domain.interfaces.ICarInteractor
import auto.testtask.viewmodel.BaseViewModel
import javax.inject.Inject

class ManufacturesViewModel @Inject constructor(
    private val carInterractor: ICarInteractor
) : BaseViewModel() {

  fun requestManufactures(){
      carInterractor.getManufactures(ManufacturesRequest(page = 7)).subscribe(createSingleObserver(next = {

      }))
  }
}