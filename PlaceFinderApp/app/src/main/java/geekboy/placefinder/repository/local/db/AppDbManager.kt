package geekboy.placefinder.repository.local.db

import android.content.Context
import androidx.room.Room
import geekboy.placefinder.repository.local.db.favsearch.FavoritePlaceDao
import geekboy.placefinder.repository.local.db.recentsearch.RecentSearchDao
import javax.inject.Inject

class AppDbManager @Inject constructor(var context: Context) : AppDBSource {

   private val placeDB: PlaceDb

    init{
        placeDB = Room
            .databaseBuilder(context, PlaceDb::class.java, "nearby_place.db")
            .build()
    }


    override fun getRecentSearchDao(): RecentSearchDao = placeDB.recentSearchDao()

    override fun getFavoriteSearchDao(): FavoritePlaceDao = placeDB.favoritePlaceDao()
}