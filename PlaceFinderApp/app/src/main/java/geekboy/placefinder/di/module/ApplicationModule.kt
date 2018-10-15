package geekboy.placefinder.di.module

import android.app.Application
import android.content.Context
import com.placefinder.di.qualifiers.PreferenceInfo
import dagger.Module
import dagger.Provides
import geekboy.placefinder.data.AppDataManager
import geekboy.placefinder.data.DataManager
import geekboy.placefinder.data.local.pref.AppPreferenceManager
import geekboy.placefinder.data.local.pref.PreferenceSource
import geekboy.placefinder.data.remote.RemoteDataManager
import geekboy.placefinder.data.remote.RemoteSource
import geekboy.placefinder.utils.PREF_NAME
import geekboy.placefinder.viewmodel.ViewModelModule
import javax.inject.Singleton


@Module(includes = [(ViewModelModule::class)])
class ApplicationModule {
    @Provides
    @Singleton
    internal fun bindContext(application: Application): Context = application

    @Provides
    @Singleton
    internal fun getRemoteSource(remoteDataManager: RemoteDataManager): RemoteSource = remoteDataManager

    @Provides
    @Singleton
    internal fun getPreferenceSource(appPreferenceManager: AppPreferenceManager):
            PreferenceSource = appPreferenceManager

    @Provides
    @Singleton
    internal fun provideDataManger(appDataManager: AppDataManager): DataManager = appDataManager



    @Provides
    @PreferenceInfo
    internal fun providePreferenceName(): String {
        return PREF_NAME
    }
}