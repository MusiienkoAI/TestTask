package auto.testtask.di.modules


import auto.testtask.ui.fragments.ManufacturesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class MainFragmentsModule {


    @ContributesAndroidInjector()
    abstract fun contributeManufacturesFragment(): ManufacturesFragment


}