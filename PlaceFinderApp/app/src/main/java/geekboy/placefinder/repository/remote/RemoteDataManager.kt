package geekboy.placefinder.data.remote

import geekboy.placefinder.utils.retrofit.ApiResponse
import geekboy.placefinder.utils.retrofit.LiveDataCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject





class RemoteDataManager @Inject constructor() : RemoteSource {
    private var iApiMethods: IApiMethods

    private val client = OkHttpClient.Builder()
    private val retrofitInstance = Retrofit.Builder()

            .baseUrl("")
            .client(client.build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())

            .build()

    init {

        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
        client.addInterceptor(loggingInterceptor)

        iApiMethods = retrofitInstance.create(IApiMethods::class.java)

    }



}