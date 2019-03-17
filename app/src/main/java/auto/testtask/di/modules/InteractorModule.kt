package auto.testtask.di.modules


import auto.domain.implementations.CarInteractorImpl
import auto.domain.interfaces.ICarInteractor
import dagger.Binds
import dagger.Module


@Module
abstract class InteractorModule {

    @Binds
    abstract fun bindCarInteractor(userInteractor: CarInteractorImpl): ICarInteractor

}