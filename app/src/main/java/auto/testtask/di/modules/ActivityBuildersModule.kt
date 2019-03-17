package auto.testtask.di.modules


import auto.testtask.ui.activities.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ActivityBuildersModule {


    @ContributesAndroidInjector(modules = [MainFragmentsModule::class])
    abstract fun contributeMainActivity(): MainActivity
}