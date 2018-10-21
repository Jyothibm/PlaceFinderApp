package geekboy.placefinder

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import geekboy.placefinder.data.AppDataManager
import geekboy.placefinder.data.remote.IApiMethods
import geekboy.placefinder.mvvm.search.SearchViewModel
import geekboy.placefinder.repository.model.places.PlacesResponse
import geekboy.placefinder.utils.API_KEY
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.*
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.Mockito.mock
import org.mockito.Mockito.times
import org.mockito.MockitoAnnotations
import retrofit2.Response

@RunWith(JUnit4::class)
open class SearchViewModelTest {

    @Rule
    @JvmField
    val instantExecutorRule = InstantTaskExecutorRule()

    private val appDatamanager = mock(AppDataManager::class.java)

    private lateinit var searchViewModel: SearchViewModel

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        searchViewModel = Mockito.spy(SearchViewModel(appDatamanager))

    }

    @Test
    fun getPlaceInformationByEmptyString(){
        searchViewModel.searchString.set("")
        searchViewModel.currentLocation ="19.205980,72.866060"

        searchViewModel.getPlaceInformation()

        Mockito.verify(appDatamanager,Mockito.never()).getNearbyPlacesData(searchViewModel.currentLocation
            , searchViewModel.searchString.get().toString())
    }

    @Test
    fun getPlaceInformationByEmptyLocation(){
        searchViewModel.searchString.set("ATM")
        searchViewModel.currentLocation =""


        searchViewModel.getPlaceInformation()


        Mockito.verify(appDatamanager,Mockito.never()).getNearbyPlacesData(searchViewModel.currentLocation
            , searchViewModel.searchString.get().toString())
    }



    @Test
    fun checkSearchString() {
        searchViewModel.searchString.set("Foo")
        Assert.assertEquals("Foo", searchViewModel.searchString.get().toString())
    }

}