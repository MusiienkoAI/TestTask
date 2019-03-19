package auto.data.services.db.interfaces

import auto.data.entities.responses.MainTypesResponse
import auto.data.entities.room.MainType
import io.reactivex.Single

interface IMainTypesDbService{
    fun saveMainTypes(mainTypesResponse: MainTypesResponse): Single<MainTypesResponse>

    fun getMainType(id: String): Single<MainType>
}