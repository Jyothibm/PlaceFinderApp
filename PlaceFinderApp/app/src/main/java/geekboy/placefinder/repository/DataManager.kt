package geekboy.placefinder.data

import geekboy.placefinder.data.local.pref.PreferenceSource
import geekboy.placefinder.data.remote.RemoteSource


interface DataManager: RemoteSource, PreferenceSource {

    enum class LoggedInMode private constructor(val type: Int) {

        LOGGED_IN_MODE_LOGGED_OUT(0),
        LOGGED_IN_MODE_GOOGLE(1),
        LOGGED_IN_MODE_FB(2),
        LOGGED_IN_MODE_SERVER(3)
    }

}