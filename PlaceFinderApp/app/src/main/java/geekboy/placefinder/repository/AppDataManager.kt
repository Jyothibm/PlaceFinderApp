package geekboy.placefinder.data

import android.content.Context
import geekboy.placefinder.data.local.pref.AppPreferenceManager
import geekboy.placefinder.data.remote.RemoteDataManager
import geekboy.placefinder.repository.local.db.AppDbManager
import geekboy.placefinder.repository.local.db.favsearch.FavoritePlaceDao
import geekboy.placefinder.repository.local.db.recentsearch.RecentSearchDao
import geekboy.placefinder.repository.model.places.PlacesResponse
import kotlinx.coroutines.experimental.Deferred
import retrofit2.Response


import javax.inject.Inject
import javax.inject.Singleton

@Singleton
open class AppDataManager : DataManager {

    private var remoteDataManager: RemoteDataManager

    private var appPreferenceManager: AppPreferenceManager

    private var appRoomDataManager: AppDbManager

    private var context: Context

    @Inject
    constructor(
        context: Context, remoteDataManager: RemoteDataManager,
        preferenceManager: AppPreferenceManager, roomDataManager: AppDbManager
    ) {
        this@AppDataManager.remoteDataManager = remoteDataManager
        this@AppDataManager.context = context
        this@AppDataManager.appPreferenceManager = preferenceManager
        this@AppDataManager.appRoomDataManager = roomDataManager
    }

    override fun getRecentSearchDao(): RecentSearchDao {
        return appRoomDataManager.getRecentSearchDao()
    }

    override fun getFavoriteSearchDao(): FavoritePlaceDao {
        return appRoomDataManager.getFavoriteSearchDao()
    }

    override fun getCurrentUserLoggedInMode(): Int =
        appPreferenceManager.getCurrentUserLoggedInMode()

    override fun setCurrentUserLoggedInMode(mode: DataManager.LoggedInMode) {
        appPreferenceManager.setCurrentUserLoggedInMode(mode)
    }


    override fun getCurrentUserId(): String = appPreferenceManager.getCurrentUserId()

    override fun setCurrentUserId(userID: String) {
        appPreferenceManager.setCurrentUserId(userID)
    }

    override fun clearUserData() {
        appPreferenceManager.clearUserData()
    }

    override fun getNearbyPlacesData(location: String, searchQuery: String): Deferred<Response<PlacesResponse>> {
        return remoteDataManager.getNearbyPlacesData(location, searchQuery)
    }

}