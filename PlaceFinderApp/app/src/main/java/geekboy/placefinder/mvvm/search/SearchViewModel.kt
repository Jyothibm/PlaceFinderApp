package geekboy.placefinder.mvvm.search

import android.util.Log
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import geekboy.placefinder.data.AppDataManager
import geekboy.placefinder.repository.model.places.PlacesResponse
import javax.inject.Inject

class SearchViewModel
@Inject constructor(appDataManager: AppDataManager) : ViewModel() {

    val placeData = MutableLiveData<PlacesResponse>()
    val loading = ObservableField<Boolean>(false)
    var searchString = ObservableField<String>("")
    var currentLocation = ""

    fun getPlaceInformation() {

    }

    fun onSearchButtonClicked() {
        Log.d("SearchString","Search:${searchString.get()} and $currentLocation")
        loading.set(true)
    }

}