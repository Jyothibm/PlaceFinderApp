package geekboy.placefinder.mvvm.places

import androidx.databinding.ObservableField
import androidx.lifecycle.ViewModel
import geekboy.placefinder.data.AppDataManager
import javax.inject.Inject

class PlacesViewModel
@Inject constructor(appDataManager: AppDataManager): ViewModel() {
    val isMapView = ObservableField<Boolean>(true)
    val searchTitle = ObservableField<String>("")
}