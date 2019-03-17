package auto.testtask.di.modules


import auto.data.services.db.IUserDbService
import auto.data.services.db.UserDbServiceImpl
import dagger.Binds
import dagger.Module


@Module
abstract class ServiceModule {
    @Binds
    abstract fun bindUserDbService(userDbServiceImpl: UserDbServiceImpl): IUserDbService

}