package geekboy.placefinder.mvvm.search

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import geekboy.placefinder.data.AppDataManager
import geekboy.placefinder.repository.model.places.PlacesResponse
import javax.inject.Inject

class SearchViewModel
@Inject constructor(appDataManager: AppDataManager) : ViewModel() {

    val placeData = MutableLiveData<PlacesResponse>()

    fun getPlaceInformation(searchQuery: String, currentLatLng: String){

    }

}