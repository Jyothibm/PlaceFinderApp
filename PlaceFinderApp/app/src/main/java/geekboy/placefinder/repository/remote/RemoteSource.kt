package geekboy.placefinder.data.remote

import geekboy.placefinder.repository.model.places.PlacesResponse
import kotlinx.coroutines.Deferred
import retrofit2.Response


interface RemoteSource {

    fun getNearbyPlacesData(location: String, searchQuery: String): Deferred<Response<PlacesResponse>>

}