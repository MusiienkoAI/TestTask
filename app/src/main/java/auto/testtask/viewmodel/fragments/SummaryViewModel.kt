package auto.testtask.viewmodel.fragments

import auto.domain.interfaces.ICarInteractor
import auto.testtask.viewmodel.BaseViewModel
import javax.inject.Inject

class SummaryViewModel @Inject constructor(
        private val carInterractor: ICarInteractor
) : BaseViewModel() {

}