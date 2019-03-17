package auto.data.services.db

import auto.data.entities.room.CarData
import io.reactivex.Single


interface IUserDbService {

    fun saveUser(carData: CarData): Single<CarData>

    fun getUser(): Single<CarData>
}