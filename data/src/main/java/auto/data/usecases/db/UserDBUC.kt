package auto.data.usecases.db

import auto.data.entities.room.CarData
import auto.data.services.db.IUserDbService
import auto.data.usecases.BaseUseCase
import auto.utilities.testing.OpenForTesting

import io.reactivex.Single
import javax.inject.Inject


@OpenForTesting
class UserDBUC @Inject constructor(private val userDbService: IUserDbService) : BaseUseCase() {

    fun saveUser(carData: CarData): Single<CarData> {
        return userDbService.saveUser(carData)
    }

    fun getUser(): Single<CarData> {
        return userDbService.getUser()
    }
}