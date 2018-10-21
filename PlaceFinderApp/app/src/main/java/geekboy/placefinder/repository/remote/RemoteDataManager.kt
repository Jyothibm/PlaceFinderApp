package geekboy.placefinder.data.remote

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import geekboy.placefinder.repository.model.places.PlacesResponse
import geekboy.placefinder.utils.API_KEY
import kotlinx.coroutines.Deferred
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject


class RemoteDataManager @Inject constructor() : RemoteSource {


    private var iApiMethods: IApiMethods

    private val client = OkHttpClient.Builder()
    private val retrofitInstance = Retrofit.Builder()

        .baseUrl("https://maps.googleapis.com/maps/api/place/nearbysearch/")
        .client(client.build())
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .build()

    init {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        client.addInterceptor(loggingInterceptor)

        iApiMethods = retrofitInstance.create(IApiMethods::class.java)

    }

    override fun getNearbyPlacesData(location: String, searchQuery: String): Deferred<Response<PlacesResponse>> {
        return iApiMethods.getPlacesData(
            loc = location,
            radius = "300",
            searchQuery = searchQuery,
            key = API_KEY
        )
    }

}