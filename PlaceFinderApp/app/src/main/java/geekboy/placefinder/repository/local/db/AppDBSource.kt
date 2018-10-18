package geekboy.placefinder.repository.local.db

import geekboy.placefinder.repository.local.db.favsearch.FavoritePlaceDao
import geekboy.placefinder.repository.local.db.recentsearch.RecentSearchDao

interface AppDBSource {

    fun getRecentSearchDao(): RecentSearchDao
    fun getFavoriteSearchDao(): FavoritePlaceDao
}