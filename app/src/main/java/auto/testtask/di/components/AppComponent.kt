package auto.testtask.di.components

import android.app.Application
import auto.testtask.App
import auto.testtask.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import javax.inject.Singleton


@Singleton
@Component(
    modules = [
        AppModule::class,
        NetworkModule::class,
        ServiceModule::class,
        DatabaseModule::class,
        ViewModelModule::class,
        InteractorModule::class,
        AndroidInjectionModule::class,
        ActivityBuildersModule::class
    ]
)
interface AppComponent {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun networkModule(networkModule: NetworkModule): Builder

        fun build(): AppComponent
    }

    fun inject(app: App)
}