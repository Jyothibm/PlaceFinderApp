package geekboy.placefinder.repository.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import geekboy.placefinder.repository.local.db.favsearch.FavoritePlace
import geekboy.placefinder.repository.local.db.favsearch.FavoritePlaceDao
import geekboy.placefinder.repository.local.db.recentsearch.RecentSearch
import geekboy.placefinder.repository.local.db.recentsearch.RecentSearchDao


@Database(
    entities = [
        RecentSearch::class,
        FavoritePlace::class
    ],
    version = 1,
    exportSchema = false
)
abstract class PlaceDb : RoomDatabase() {
    abstract fun recentSearchDao(): RecentSearchDao
    abstract fun favoritePlaceDao(): FavoritePlaceDao
}