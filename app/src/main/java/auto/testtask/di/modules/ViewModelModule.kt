package auto.testtask.di.modules


import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import auto.testtask.di.ViewModelKey
import auto.testtask.viewmodel.AppViewModelFactory
import auto.testtask.viewmodel.fragments.BuildDatesViewModel
import auto.testtask.viewmodel.fragments.MainTypesViewModel
import auto.testtask.viewmodel.fragments.ManufacturesViewModel
import auto.testtask.viewmodel.fragments.SummaryViewModel
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
    @IntoMap
    @ViewModelKey(MainTypesViewModel::class)
    abstract fun bindMainTypesViewModel(mainTypesViewModel: MainTypesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BuildDatesViewModel::class)
    abstract fun bindBuildDatesViewModel(buildDatesViewModel: BuildDatesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(SummaryViewModel::class)
    abstract fun bindSummaryViewModel(summaryViewModel: SummaryViewModel): ViewModel



    @Binds
    abstract fun bindViewModelFactory(factory: AppViewModelFactory): ViewModelProvider.Factory
}