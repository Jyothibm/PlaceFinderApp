package geekboy.placefinder.data.remote

import geekboy.placefinder.repository.model.places.PlacesResponse
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query


interface IApiMethods {

    @GET("json")
    fun getPlacesData(@Query("location")loc: String,
                      @Query("radius")radius: String,
                      @Query("types")searchQuery: String,
                      @Query("key")key: String): Deferred<Response<PlacesResponse>>

}