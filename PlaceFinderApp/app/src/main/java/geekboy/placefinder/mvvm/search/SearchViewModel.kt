package geekboy.placefinder.mvvm.search

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import geekboy.placefinder.data.AppDataManager
import geekboy.placefinder.repository.local.db.recentsearch.RecentSearch
import geekboy.placefinder.repository.model.places.PlacesResponse
import kotlinx.coroutines.*
import retrofit2.Response
import javax.inject.Inject

open class SearchViewModel
@Inject constructor(val appDataManager: AppDataManager) : ViewModel() {

    private val viewModelJob = Job()
    private val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    val placeData = MutableLiveData<Response<PlacesResponse>>()
    val loading = ObservableField<Boolean>(false)
    var searchString = ObservableField<String>("")
    var currentLocation = ""

    fun getPlaceInformation() {

        if (currentLocation.isEmpty()){return}
        if (searchString.get().toString().isEmpty()){return}

        uiScope.launch {
            var response = appDataManager.getNearbyPlacesData(currentLocation, searchString.get().toString()).await()
            if (response.code() == 200) {
                launch(Dispatchers.IO) {
                    appDataManager.getRecentSearchDao().insert(RecentSearch(id = null, name = searchString.get().toString()))
                    viewModelJob.join()
                }
                placeData.value = response
            } else {
                // Load DB details
                placeData.value = response
            }
            loading.set(false)
        }
    }

    fun onSearchButtonClicked() {
        loading.set(true)
        getPlaceInformation()
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}