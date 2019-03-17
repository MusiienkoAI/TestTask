package auto.data.services.db

import auto.data.db.CarDatabase
import auto.data.entities.room.CarData
import auto.data.services.roomutil.RoomObservable
import auto.utilities.testing.OpenForTesting

import io.reactivex.Single
import io.reactivex.functions.Function
import javax.inject.Inject


@OpenForTesting
class UserDbServiceImpl @Inject constructor(
    val carDatabase: CarDatabase
) : IUserDbService {

    override fun saveUser(carData: CarData): Single<CarData> = RoomObservable.createObject(
        carDatabase,
        Function<CarDatabase, CarData> {
            it.userDao().saveUser(carData)
            return@Function carData
        }
    ).firstOrError()

    override fun getUser(): Single<CarData> = RoomObservable.createObject(
        carDatabase,
        Function<CarDatabase, CarData> {
            it.userDao().getUser() ?: throw UnknownError()
        }
    ).firstOrError()
}