package geekboy.placefinder.mvvm.places.placelist

import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import geekboy.placefinder.data.AppDataManager
import geekboy.placefinder.repository.local.db.favsearch.FavoritePlace
import geekboy.placefinder.repository.model.places.GeoAddressModel
import geekboy.placefinder.repository.model.places.GeoLocation
import geekboy.placefinder.repository.model.places.OpeningHourModel
import geekboy.placefinder.repository.model.places.PlacesModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class PlaceListViewModel
@Inject constructor(val appDataManager: AppDataManager) : ViewModel() {
    private val viewModelJob = Job()

    private val uiScope = CoroutineScope(Dispatchers.IO + viewModelJob)

    val placeList = MutableLiveData<ArrayList<PlacesModel>>()

    fun insertInDb(placesModel: PlacesModel) {
        uiScope.launch {
            var rating = "N/A"
            if (placesModel.rating!=null){
                rating = placesModel.rating.toString()
            }

            val favoritePlace = FavoritePlace(
                placesModel?.placeID ?: "",
                placesModel?.placeName ?: "",
                placesModel.address ?: "",
                 rating,
                placesModel?.openingHour?.openNow ?: false
            )
            appDataManager.getFavoriteSearchDao().insert(favoritePlace)
        }
    }

    fun getAllFavoritePlaces() {
        uiScope.launch {
            val result = appDataManager.getFavoriteSearchDao().loadFavoritePlaces() as ArrayList<FavoritePlace>
            val placesData = ArrayList<PlacesModel>()
            result.map {
                var rating = 0.0
                 if (it.rating!=("N/A") && it.rating.isNotEmpty()){
                     rating = it.rating.toDouble()
                 }
                placesData.add(

                    PlacesModel(
                        it.id,
                        it.name,
                        GeoAddressModel(GeoLocation(0.0, 0.0)),
                        rating,
                        it.address,
                        OpeningHourModel(it.openStatus),
                        true
                    )
                )
            }

            placeList.postValue(placesData)
        }
    }

    fun deleteInDb(placesModel: PlacesModel) {
        uiScope.launch {
            appDataManager.getFavoriteSearchDao().deleteFavoritePlace(placesModel?.placeID ?: "")
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}