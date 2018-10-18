package geekboy.placefinder.di.module

import android.app.Application
import android.content.Context
import android.content.ReceiverCallNotAllowedException
import androidx.room.Room
import com.placefinder.di.qualifiers.PreferenceInfo
import dagger.Module
import dagger.Provides
import geekboy.placefinder.data.AppDataManager
import geekboy.placefinder.data.DataManager
import geekboy.placefinder.data.local.pref.AppPreferenceManager
import geekboy.placefinder.data.local.pref.PreferenceSource
import geekboy.placefinder.data.remote.RemoteDataManager
import geekboy.placefinder.data.remote.RemoteSource
import geekboy.placefinder.repository.local.db.AppDBSource
import geekboy.placefinder.repository.local.db.AppDbManager
import geekboy.placefinder.repository.local.db.PlaceDb
import geekboy.placefinder.repository.local.db.favsearch.FavoritePlaceDao
import geekboy.placefinder.repository.local.db.recentsearch.RecentSearchDao
import geekboy.placefinder.utils.PREF_NAME
import geekboy.placefinder.viewmodel.ViewModelModule
import javax.inject.Singleton


@Module(includes = [(ViewModelModule::class)])
open class ApplicationModule {
    @Provides
    @Singleton
    internal fun bindContext(application: Application): Context = application

    @Provides
    @Singleton
    fun getRemoteSource(remoteDataManager: RemoteDataManager): RemoteSource = remoteDataManager

    @Provides
    @Singleton
    fun getPreferenceSource(appPreferenceManager: AppPreferenceManager):
            PreferenceSource = appPreferenceManager

    @Provides
    @Singleton
    fun getRoomSource(appDbManager: AppDbManager): AppDBSource = appDbManager

    @Provides
    @Singleton
    fun provideDataManger(appDataManager: AppDataManager): DataManager = appDataManager


    @Provides
    @PreferenceInfo
    internal fun providePreferenceName(): String {
        return PREF_NAME
    }
}