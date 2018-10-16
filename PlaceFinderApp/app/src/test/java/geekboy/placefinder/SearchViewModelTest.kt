package geekboy.placefinder

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import geekboy.placefinder.data.AppDataManager
import geekboy.placefinder.di.component.DaggerAppComponent
import geekboy.placefinder.di.module.ApplicationModule
import geekboy.placefinder.mvvm.search.SearchViewModel
import org.junit.BeforeClass
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mockito
import org.mockito.Mockito.mock
import javax.inject.Inject

@RunWith(JUnit4::class)
class SearchViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val appDatamanager = mock(AppDataManager::class.java)

    private val searchViewModel = SearchViewModel(appDatamanager)

    @Test
    fun checkPlacesNotNull() {

    }

    @Test
    fun checkSearchString(){

    }

}