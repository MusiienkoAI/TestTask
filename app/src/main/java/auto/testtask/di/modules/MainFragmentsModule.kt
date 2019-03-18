package auto.testtask.di.modules


import auto.testtask.ui.fragments.BuildDatesFragment
import auto.testtask.ui.fragments.MainTypesFragment
import auto.testtask.ui.fragments.ManufacturesFragment
import auto.testtask.ui.fragments.SummaryFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class MainFragmentsModule {


    @ContributesAndroidInjector()
    abstract fun contributeManufacturesFragment(): ManufacturesFragment

    @ContributesAndroidInjector()
    abstract fun contributeMainTypeFragment(): MainTypesFragment


    @ContributesAndroidInjector()
    abstract fun contributeBuildDateFragment(): BuildDatesFragment


    @ContributesAndroidInjector()
    abstract fun contributeSummaryFragment(): SummaryFragment


}