package geekboy.placefinder.data.remote

import geekboy.placefinder.repository.model.places.PlacesResponse
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response


interface RemoteSource {

    fun getNearbyPlacesData(location: String, searchQuery: String): Deferred<Response<PlacesResponse>>

}