package geekboy.placefinder

import geekboy.placefinder.data.AppDataManager
import geekboy.placefinder.di.component.DaggerAppComponent
import geekboy.placefinder.di.module.ApplicationModule
import org.junit.BeforeClass
import javax.inject.Inject

class SearchViewModelTest {

    @Inject
    lateinit var appDataManager: AppDataManager

    @BeforeClass
    fun initDaggerGraph(){

    }

}