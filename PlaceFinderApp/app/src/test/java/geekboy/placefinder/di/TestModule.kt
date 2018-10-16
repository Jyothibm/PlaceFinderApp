package geekboy.placefinder.di

import dagger.Module
import dagger.Provides
import geekboy.placefinder.data.AppDataManager
import geekboy.placefinder.data.DataManager
import geekboy.placefinder.di.module.ApplicationModule
import org.mockito.Mockito.mock
import javax.inject.Singleton

@Module
open class TestModule {

    @Provides @Singleton open fun getAppDataManager() = mock(AppDataManager::class.java)


}