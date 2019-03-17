package auto.data.services.roomutil

import auto.data.db.CarDatabase
import auto.data.entities.RoomNullable
import auto.utilities.exceptions.RoomError
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.functions.Function


object RoomObservable {

    fun <Data> createObject(carDatabase: CarDatabase, function: Function<CarDatabase, Data>): Flowable<Data> {
        return Flowable.create(object : OnSubscribeRoom<Data>(carDatabase) {
            override fun get(): Data? {
                return try {
                    function.apply(carDatabase)
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            }
        }, BackpressureStrategy.LATEST)
            .map {
                if (it.data == null) throw RoomError(message = "Data is null")
                else it.data
            }
    }

    fun <Data> createNullableObject(
        carDatabase: CarDatabase, function: Function<CarDatabase, Data>
    ): Flowable<RoomNullable<Data?>> {
        return Flowable.create(object : OnSubscribeRoom<Data>(carDatabase) {
            override fun get(): Data? {
                return try {
                    function.apply(carDatabase)
                } catch (e: Exception) {
                    e.printStackTrace()
                    null
                }
            }
        }, BackpressureStrategy.LATEST)
    }
}