package geekboy.weatherapp.data

import android.content.Context

import geekboy.weatherapp.data.local.pref.AppPreferenceManager
import geekboy.weatherapp.data.remote.RemoteDataManager

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppDataManager : DataManager {
    private var remoteDataManager: RemoteDataManager

    private var appPreferenceManager: AppPreferenceManager

    private var context: Context

    @Inject
    constructor(context: Context, remoteDataManager: RemoteDataManager, preferenceManager: AppPreferenceManager) {
        this@AppDataManager.remoteDataManager = remoteDataManager
        this@AppDataManager.context = context
        this@AppDataManager.appPreferenceManager = preferenceManager
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


}