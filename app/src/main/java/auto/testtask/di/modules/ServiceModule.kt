package auto.testtask.di.modules


import auto.data.services.db.implementors.BuildDatesDbServiceImpl
import auto.data.services.db.implementors.MainTypesDbServiceImpl
import auto.data.services.db.implementors.ManufacturesDbServiceImpl
import auto.data.services.db.interfaces.IBuildDatesDbService
import auto.data.services.db.interfaces.IMainTypesDbService
import auto.data.services.db.interfaces.IManufacturesDbService
import dagger.Binds
import dagger.Module


@Module
abstract class ServiceModule {
    @Binds
    abstract fun bindManufacturesDbService(manufacturesDbServiceImpl: ManufacturesDbServiceImpl): IManufacturesDbService

    @Binds
    abstract fun bindMainTypesDbService(mainTypesDbServiceImpl: MainTypesDbServiceImpl): IMainTypesDbService

    @Binds
    abstract fun bindBuildDatesDbService(buildDatesDbServiceImpl: BuildDatesDbServiceImpl): IBuildDatesDbService


}