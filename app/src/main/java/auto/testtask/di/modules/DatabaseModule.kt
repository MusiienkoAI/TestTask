package auto.testtask.di.modules

import android.app.Application
import androidx.room.Room
import auto.data.db.CarDatabase
import auto.testtask.BuildConfig
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideKlipDb(application: Application): CarDatabase {
        return Room
            .databaseBuilder(
                application,
                CarDatabase::class.java,
                String.format("%s.db", BuildConfig.APPLICATION_ID)
            )
            .fallbackToDestructiveMigration()
            .build()
    }
}
