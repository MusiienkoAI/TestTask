package auto.testtask.di.modules


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import auto.testtask.di.ViewModelKey
import auto.testtask.viewmodel.AppViewModelFactory
import auto.testtask.viewmodel.fragments.ManufacturesViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ManufacturesViewModel::class)
    abstract fun bindManufacturesViewModel(manufacturesViewModel: ManufacturesViewModel): ViewModel


    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}